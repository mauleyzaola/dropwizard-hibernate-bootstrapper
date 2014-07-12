package io.github.mauleyzaola.bootstrapper;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.github.mauleyzaola.common.dao.DaoAddress;
import io.github.mauleyzaola.common.dao.DaoCountry;
import io.github.mauleyzaola.common.entities.Address;
import io.github.mauleyzaola.common.entities.Country;
import io.github.mauleyzaola.common.resources.ServiceAddress;
import io.github.mauleyzaola.common.resources.ServiceCountry;
import io.github.mauleyzaola.config.ApiConfiguration;

//Esta es la clase que lanza la aplicacion
public class App extends Application<ApiConfiguration> {

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    /*
    Configuracion de Hibernate con Dropwizard para poder inyectar el codigo en cualquier servicio
    Todas las clases que se agreguen aqui, seran generadas automaticamente en nuestra base de datos
    La estrategia depende de la configuracion del parametro hibernate.hbm2ddl.auto en configuration.yml, en este caso es UPDATE
    */
    private final HibernateBundle<ApiConfiguration> hibernateBundle = new
            HibernateBundle<ApiConfiguration>(Address.class, Country.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(ApiConfiguration apiConfiguration) {
                    return apiConfiguration.getDataSourceFactory();
                }
            };

    //Agregar Hibernate a la configuracion
    @Override
    public void initialize(Bootstrap<ApiConfiguration> bootstrap) {

        //hibernate bootstrapping
        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(new MigrationsBundle<ApiConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(ApiConfiguration configuration){
                return configuration.getDataSourceFactory();
            }
        });
    }

    //Ejecuta la API de Dropwizard y agrega los servicios necesarios
    @Override
    public void run(final ApiConfiguration configuration, final Environment environment) throws ClassNotFoundException  {
        configureServices(environment);
    }

    //Instanciar cada dao y agregar al mix los servicios que deseamos exponer a nuestra API
    private void configureServices(Environment environment){
        DaoCountry country = new DaoCountry(hibernateBundle.getSessionFactory());
        DaoAddress address = new DaoAddress(hibernateBundle.getSessionFactory());

        environment.jersey().register(new ServiceCountry(country));
        environment.jersey().register(new ServiceAddress(address));
    }
}