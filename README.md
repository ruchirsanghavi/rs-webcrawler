# rs-webcrawler

## Build!
You need:
* Apache Maven
* JDK 8  (with JAVA_HOME set correctly)

To build this project from the project root directory please run:
    
    mvn clean package


## Run!

To run the application, from the project root directory:
    
    java -jar target/rs-webcrawler-1.0-SNAPSHOT.jar <URL>
    
where `<URL>` is a URL of your choice (including the HTTP bit!)

e.g.
    
    java -jar target/rs-webcrawler-1.0-SNAPSHOT.jar http://wiprodigital.com
    
### Configure Proxy Server

If you are behind a proxy server, you need to pass the proxy settings through to the application with appropriate values:

    
    java -Dhttp.proxyHost=<proxy hostname> -Dhttp.proxyPort=<proxy port> -jar target/rs-webcrawler-1.0-SNAPSHOT.jar http://wiprodigital.com


e.g.
    
    java -Dhttp.proxyHost=webproxy.mycorp.com -Dhttp.proxyPort=8080 -jar target/rs-webcrawler-1.0-SNAPSHOT.jar http://wiprodigital.com

 Note: If you are connecting to an HTTPS site, then use "https" instead of "http" above, e.g. "https.proxyHost"
 For more information see: https://docs.oracle.com/javase/8/docs/technotes/guides/net/proxies.html

## Output
Once the program is running, it will TRACE out all the links that it is extracting, along with any it failed to do.
Once completed, to view the extracted links, simply view the the results.json file that is generated.

The results are in the form of nested JSON with child web pages with links and their relevant types.

An example output is available in the project used on the URL "http://wiprodigital.com", called [example-results.json](example-results.json)


### Configure Logging
To configure logging levels (default set to TRACE), modify the level in [application.yml](https://github.com/ruchirsanghavi/rs-webcrawler/tree/master/src/main/resources/application.yml) file.


## NOTES
* At its heart, it's a basic recursion algorithm, taking into account web pages that have already been visited, and ignoring any pages that are unreachable.
* Only <a> links on a given page are visited, other types are assumed to be imports or media.
* A 3rd party library called JSOUP is used to navigate the HTML documents (https://jsoup.org/)

## FUTURE
* The web crawler can be multi-threaded, using work managers, or other thread pools.
* More unit testing
* As Spring is used in the application, there is potential for Spring Integration Tests to be used.
* Improve the error handling when invalid URLs are passed into the application, or when a page has links to invalid URLs.