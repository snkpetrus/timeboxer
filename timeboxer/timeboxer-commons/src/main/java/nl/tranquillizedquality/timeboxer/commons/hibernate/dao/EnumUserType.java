package nl.tranquillizedquality.timeboxer.commons.hibernate.dao;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Implementation of a {@link UserType} for an enumeration that needs to
 * transformed into a VARCHAR.
 * 
 * @author Salomo Petrus (salomo.petrus@tele2.com)
 * @since 3 jun. 2011
 * @param <E>
 *            The enum
 */
public class EnumUserType<E extends Enum<E>> implements UserType, Serializable {

	/**
	 * Unique identifier for serialization.
	 */
	private static final long serialVersionUID = -6250584776233871187L;

	/** The enum class. */
	private Class<E> clazz = null;

	/** The SQL types used. */
	private static final int[] SQL_TYPES = { Types.VARCHAR };

	/**
	 * Protected constructor taking taking the enum class.
	 * 
	 * @param c
	 *            The enum class.
	 */
	protected EnumUserType(final Class<E> c) {
		this.clazz = c;
	}

	@Override
	public int[] sqlTypes() {
		return SQL_TYPES;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Class returnedClass() {
		return clazz;
	}

	@Override
	public Object nullSafeGet(final ResultSet resultSet, final String[] names, final SessionImplementor sessionImplementor, final Object owner) throws HibernateException, SQLException {
		final String name = resultSet.getString(names[0]);
		E result = null;
		if (!resultSet.wasNull()) {
			result = Enum.valueOf(clazz, name);
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void nullSafeSet(final PreparedStatement preparedStatement, final Object value, final int index, final SessionImplementor sessionImplementor) throws HibernateException, SQLException {
		if (null == value) {
			preparedStatement.setNull(index, Types.VARCHAR);
		}
		else {
			preparedStatement.setString(index, ((Enum) value).name());
		}
	}

	@Override
	public Object deepCopy(final Object value) throws HibernateException {
		return value;
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Object assemble(final Serializable cached, final Object owner) throws HibernateException {
		return cached;
	}

	@Override
	public Serializable disassemble(final Object value) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public Object replace(final Object original, final Object target, final Object owner) throws HibernateException {
		return original;
	}

	@Override
	public int hashCode(final Object x) throws HibernateException {
		return x.hashCode();
	}

	@Override
	public boolean equals(final Object x, final Object y) throws HibernateException {
		if (x == y) {
			return true;
		}
		if (null == x || null == y) {
			return false;
		}
		return x.equals(y);
	}

}
