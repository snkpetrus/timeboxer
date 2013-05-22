package nl.tranquillizedquality.timeboxer.commons.hibernate;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Implements a generic enum user type identified / represented by a single
 * identifier / column.
 * <p>
 * <ul>
 * <li>The enum type being represented by the certain user type must be set by
 * using the 'enumClass' property.</li>
 * <li>The identifier representing a enum value is retrieved by the
 * identifierMethod. The name of the identifier method can be specified by the
 * 'identifierMethod' property and by default the name() method is used.</li>
 * <li>The identifier type is automatically determined by the return-type of the
 * identifierMethod.</li>
 * <li>The valueOfMethod is the name of the static factory method returning the
 * enumeration object being represented by the given indentifier. The
 * valueOfMethod's name can be specified by setting the 'valueOfMethod'
 * property. The default valueOfMethod's name is 'valueOf'.</li>
 * </ul>
 * </p>
 * <p>
 * Example of an enum type represented by an int value:<br/>
 * 
 * <pre>
 * public enum SimpleNumber {
 * 	Unknown(-1), Zero(0), One(1), Two(2), Three(3);
 * 	int value;
 * 
 * 	protected SimpleNumber(int value) {
 * 		this.value = value;
 * 	}
 * 
 * 	public int toInt() {
 * 		return value;
 * 	}
 * 
 * 	public SimpleNumber fromInt(int value) {
 * 		switch (value) {
 * 			case 0:
 * 				return Zero;
 * 			case 1:
 * 				return One;
 * 			case 2:
 * 				return Two;
 * 			case 3:
 * 				return Three;
 * 			default:
 * 				return Unknown;
 * 		}
 * 	}
 * }
 * </pre>
 * <p>
 * The Mapping would look like this:<br/>
 * 
 * <pre>
 * &lt;typedef name="SimpleNumber" class="GenericEnumUserType">
 *   &lt;param name="enumClass">SimpleNumber&lt;/param>
 *   &lt;param name="identifierMethod">toInt&lt;/param>
 *   &lt;param name="valueOfMethod">fromInt&lt;/param>
 * &lt;/typedef>
 * &lt;class ...>
 *   ...
 *   &lt;property name="number" column="number" type="SimpleNumber"/>
 * &lt;/class>
 * </pre>
 * 
 * @author Martin Kersten
 * @since 05.05.2005
 */
@SuppressWarnings("rawtypes")
public class GenericEnumUserType implements UserType, ParameterizedType {
	private static final String DEFAULT_IDENTIFIER_METHOD_NAME = "name";

	private static final String DEFAULT_VALUE_OF_METHOD_NAME = "valueOf";

	private Class<? extends Enum> enumClass;

	private Class<?> identifierType;

	private Method identifierMethod;

	private Method valueOfMethod;

	private AbstractSingleColumnStandardBasicType type;

	private int[] sqlTypes;

	@Override
	public void setParameterValues(final Properties parameters) {
		final String enumClassName = parameters.getProperty("enumClass");
		try {
			enumClass = Class.forName(enumClassName).asSubclass(Enum.class);
		}
		catch (final ClassNotFoundException cfne) {
			throw new HibernateException("Enum class not found", cfne);
		}

		final String identifierMethodName = parameters.getProperty("identifierMethod", DEFAULT_IDENTIFIER_METHOD_NAME);

		try {
			identifierMethod = enumClass.getMethod(identifierMethodName, new Class[0]);
			identifierType = identifierMethod.getReturnType();
		}
		catch (final Exception e) {
			throw new HibernateException("Failed to obtain identifier method", e);
		}

		final TypeResolver tr = new TypeResolver();
		type = (AbstractSingleColumnStandardBasicType) tr.basic(identifierType.getName());

		if (type == null) {
			throw new HibernateException("Unsupported identifier type " + identifierType.getName());
		}

		sqlTypes = new int[] { type.sqlType() };

		final String valueOfMethodName = parameters.getProperty("valueOfMethod", DEFAULT_VALUE_OF_METHOD_NAME);

		try {
			valueOfMethod = enumClass.getMethod(valueOfMethodName, new Class[] { identifierType });
		}
		catch (final Exception e) {
			throw new HibernateException("Failed to obtain valueOf method", e);
		}
	}

	@Override
	public Class<? extends Enum> returnedClass() {
		return enumClass;
	}

	@Override
	public Object nullSafeGet(final ResultSet rs, final String[] names, final SessionImplementor sessionImplementor, final Object owner) throws HibernateException, SQLException {
		final Object identifier = type.nullSafeGet(rs, names[0], sessionImplementor);
		if (identifier == null) {
			return null;
		}

		try {
			return valueOfMethod.invoke(enumClass, new Object[] { identifier });
		}
		catch (final Exception e) {
			throw new HibernateException("Exception while invoking valueOf method '" + valueOfMethod.getName() + "' of " + "enumeration class '" + enumClass + "'", e);
		}
	}

	@Override
	public void nullSafeSet(final PreparedStatement st, final Object value, final int index, final SessionImplementor sessionImplementor) throws HibernateException, SQLException {
		try {
			if (value == null) {
				st.setNull(index, type.sqlType());
			}
			else {
				final Object identifier = identifierMethod.invoke(value, new Object[0]);
				type.nullSafeSet(st, identifier, index, sessionImplementor);
			}
		}
		catch (final Exception e) {
			throw new HibernateException("Exception while invoking identifierMethod '" + identifierMethod.getName() + "' of " + "enumeration class '" + enumClass + "'", e);
		}
	}

	@Override
	public int[] sqlTypes() {
		return sqlTypes;
	}

	@Override
	public Object assemble(final Serializable cached, final Object owner) throws HibernateException {
		return cached;
	}

	@Override
	public Object deepCopy(final Object value) throws HibernateException {
		return value;
	}

	@Override
	public Serializable disassemble(final Object value) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public boolean equals(final Object x, final Object y) throws HibernateException {
		return x == y;
	}

	@Override
	public int hashCode(final Object x) throws HibernateException {
		return x.hashCode();
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Object replace(final Object original, final Object target, final Object owner) throws HibernateException {
		return original;
	}

}
