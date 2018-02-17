package uk.co.crawler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import uk.co.crawler.interfaces.ILinkExtracter;

import java.io.IOException;
import java.util.List;

/**
 * A command line runner to to extract links from a website.
 *
 * @author rsanghavi.
 */
public class Runner implements CommandLineRunner {

    protected static final Logger LOGGER = LoggerFactory.getLogger(Runner.class); //NOSONAR

    @Autowired
    private ConfigurableApplicationContext context;

    @Autowired
    private ILinkExtracter extracter;

    @Override
    public void run(final String... strings) throws Exception {

        try {
            final String url1 = "http://wiprodigital.com/";
            final List<String> extract = this.extracter.extract(url1);
            writeResults(extract);
        }
        finally {
            this.context.close();
        }
    }

    private void writeResults(final List<String> links) throws IOException {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final String results = gson.toJson(links);
        LOGGER.trace(results);
    }

}
