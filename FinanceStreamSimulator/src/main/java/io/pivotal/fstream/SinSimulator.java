package io.pivotal.fstream;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

public class SinSimulator implements CommandLineRunner {

	@Value("${serverUrl}") 
	private String URL;
	
	@Value("${numberOfMessages}") 
	private int numberOfMessages;

	@Value("${basePrice}") 
	private double basePrice;

	@Value("${scale}") 
	private double scale;
	
	@Value("${symbol}") 
	private String symbol;

	private RestTemplate restTemplate = new RestTemplate();
	
	Logger logger = Logger.getLogger(SinSimulator.class.getName());

	@Override
	public void run(String... args) throws Exception {
		
		List objects = restTemplate.getForObject("http://localhost:8080/gemfire-api/v1/queries/adhoc?q=SELECT%20DISTINCT%20*%20FROM%20/Stocks%20s%20ORDER%20BY%20%22entryTimestamp%22%20LIMIT%20600", List.class);
		logger.info("received number of objects "+ objects.size());
		logger.info("index 0 is "+ objects.get(0));
		
		
		logger.info("--------------------------------------");
		logger.info(">>> URL: "+URL);
		logger.info(">>> Number of messages: "+numberOfMessages);
		logger.info(">>> Base Price: "+basePrice);
		logger.info(">>> Scale: "+scale);
		logger.info(">>> Symbol: "+symbol);
		logger.info("--------------------------------------");
		
		double low = basePrice - scale;
		double high = basePrice + scale;

		logger.info(">>> Posting "+numberOfMessages+" messages ranging from "+low+" to "+high+" ...");

		
		for( int i=0; i < numberOfMessages; i++ ){
			double value = ( basePrice - Math.sin( Math.toRadians(i) ) * scale );

			StockPrice price = new StockPrice();
			price.setPrice(value);
			price.setLow(low);
			price.setHigh(high);
			price.setSymbol(symbol);
			StockPrice response = restTemplate.postForObject(URL, price, StockPrice.class);
			
		}		
		
				
		logger.info("done");
		
		
	}

}