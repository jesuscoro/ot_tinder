package net.opentrends.model.dao;

import com.orm.SugarTransactionHelper;
import com.orm.query.Condition;
import com.orm.query.Select;

import net.opentrends.domain.model.Gender;
import net.opentrends.domain.model.Person;
import net.opentrends.model.model.PersonRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementacion de la capa de acceso a datos.
 *
 * @see "http://satyan.github.io/sugar/"
 * @see net.opentrends.model.repository.datasource.DataBasePersonDataStore
 */
public class PersonDaoImpl implements PersonDao {

    /**
     * {@inheritDoc}
     * <p/>
     * Obtiene de la tabla de base de datos un listado de objetos de tipo {@link PersonRecord}
     * ordenados con los favoritos primero y por {@link PersonRecord#number}. Una vez obtenidos
     * los convierte a objetos del dominio y devuelve ese listado a traves del callback pasado
     * como parametro
     *
     * @param gender   El sexo de las personas a obtener
     * @param callback Callback a ejecutar una vez completada la peticion
     */
    @Override
    public void getPersons(Gender gender, GetPersistedPersonsCallback callback) {
        try {
            List<PersonRecord> records = Select.from(PersonRecord.class)
                    .where(Condition.prop(PersonRecord.COLUMN_GENDER).eq(gender.toString()))
                    .orderBy(PersonRecord.COLUMN_FAVOURITE + " DESC, " + PersonRecord.COLUMN_NUMBER + " ASC")
                    .list();

            final List<Person> persons = new ArrayList<>();
            for (PersonRecord pr : records) {
                persons.add(pr.toPerson());
            }

            callback.onGetPersistedPersons(persons);
        } catch (Exception e) {
            callback.onGetPersistedPersonsError(e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     * <p/>
     * Convierte los objetos {@link Person} del dominio a objetos de la capa de persistencia
     * {@link PersonRecord} y los almacena en base de datos en una unica transaccion
     *
     * @param persons  El listado de personas a persistir
     * @param callback Callback a ejecutar una vez completada la peticion
     */
    @Override
    public void savePersons(final List<Person> persons, final SavePersonsCallback callback) {
        SugarTransactionHelper.doInTransaction(new SugarTransactionHelper.Callback() {
            @Override
            public void manipulateInTransaction() {
                try {
                    for (Person p : persons) {
                        p.setId(new PersonRecord(p).save());
                    }

                    callback.onSavePersons(persons);
                } catch (Exception e) {
                    callback.onSavePersonsError(e.getMessage());
                }
            }
        });
    }
}
