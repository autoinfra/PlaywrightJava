package utilities.envPicker;

import org.aeonbits.owner.Config;

@Config.Sources({
//NOTE - COMPLILE THE PROJECT BEFORE TRYING TO READ PROP FILES
        "classpath:environment/${env}.properties"
})
public interface Environment extends Config {

    String url();

    String username();

    String password();

    @Key("db.hostname")
    String getDBHostname();

    @Key("db.port")
    int getDBPort();

    @Key("db.username")
    String getDBUsername();

    @Key("db.password")
    String getDBPassword();

}