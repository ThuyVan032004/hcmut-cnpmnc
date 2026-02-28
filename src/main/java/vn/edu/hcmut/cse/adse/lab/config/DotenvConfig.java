package vn.edu.hcmut.cse.adse.lab.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

public class DotenvConfig implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .systemProperties()
                .load();

        Map<String, Object> envVars = new HashMap<>();

        String host = dotenv.get("POSTGRES_HOST", "localhost");
        String port = dotenv.get("POSTGRES_PORT", "5432");
        String db   = dotenv.get("POSTGRES_DB",   "student_management");
        String user = dotenv.get("POSTGRES_USER",  "postgres");
        String pass = dotenv.get("POSTGRES_PASSWORD", "");

        envVars.put("POSTGRES_HOST", host);
        envVars.put("POSTGRES_PORT", port);
        envVars.put("POSTGRES_DB", db);
        envVars.put("POSTGRES_USER", user);
        envVars.put("POSTGRES_PASSWORD", pass);

        envVars.put("DATABASE_URL",
                "jdbc:postgresql://" + host + ":" + port + "/" + db);
        envVars.put("DB_USERNAME", user);
        envVars.put("DB_PASSWORD", pass);

        applicationContext.getEnvironment().getPropertySources()
                .addFirst(new MapPropertySource("dotenvProperties", envVars));
    }
}