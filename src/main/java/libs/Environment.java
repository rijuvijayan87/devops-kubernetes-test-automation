package libs;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${env}.properties"
})
public interface Environment extends Config {

    @Key("app.url")
    String getAppURL();

}
