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

An example output is available in the project used on the URL "https://buildit.wiprodigital.com/", called [example-results.json](example-results.json)


### Configure Logging
To configure logging levels (default set to TRACE), modify the level in [application.yml](https://github.com/ruchirsanghavi/rs-webcrawler/tree/master/src/main/resources/application.yml) file.


## NOTES
* At its heart, it's a basic recursion algorithm, taking into account web pages that have already been visited, and ignoring any pages that are unreachable.
* If a web page has already been visited before in the site map, then only the link is mentioned rather than crawling the page again.
* Only <a> links on a given page are visited, other types are assumed to be imports or media i.e. they are listed in the output but not crawled through.
* A 3rd party library called JSOUP is used to navigate the HTML documents (https://jsoup.org/)

## FUTURE
* More testing (perhaps even use mutation testing)
* As Spring is used in the application, there is potential for Spring Integration Tests to be used.
* Improve the error handling when invalid URLs are passed into the application, or when a page has links to invalid URLs.
* Web crawler can be exposed as a RESTful service as Spring Boot is used.
* A web based UI can be added that allows for input and display of results.

## SCALABILITY
* Scalability needs to be addressed - current design means entire site map is held in memory, for large sites, this will cause the application to run out of heap space.
* The results need to be flushed periodically to an output, as in the event that the program crashes, results are lost.
* The sites that are visited are currently stored as domain objects, instead, checksums can be kept and stored in a database of some sort.
* To improve scalability and performance the web crawler can be multi-threaded, using work managers, or other thread pools, e.g. [TaskExecutor](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/core/task/TaskExecutor.html).