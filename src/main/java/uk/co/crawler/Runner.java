package uk.co.crawler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import uk.co.crawler.interfaces.ILinkExtracter;
import uk.co.crawler.pages.WebPage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * A command line runner to to extract links from a website.
 *
 * @author rsanghavi.
 */
public class Runner implements CommandLineRunner {

    private static final String RESULTS_TXT = "results.json";

    @Autowired
    private ConfigurableApplicationContext context;

    @Autowired
    private ILinkExtracter extracter;

    @Override
    public void run(final String... strings) throws Exception {

        try {
            final String url1 = "http://wiprodigital.com";
            final List<WebPage> extract = this.extracter.extract(url1);
            writeResults(extract);
        } finally {
            this.context.close();
        }
    }

    private void writeResults(final List<WebPage> links) throws IOException {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final String results = gson.toJson(links);
        Files.write(Paths.get(RESULTS_TXT), results.getBytes());
    }

}
