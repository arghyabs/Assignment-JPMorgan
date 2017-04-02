package com.jpmorgan.exercise.stockmarket.simplestock.calculation;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import com.jpmorgan.exercise.stockmarket.simplestock.calculation.beans.Stock;
import com.jpmorgan.exercise.stockmarket.simplestock.calculation.beans.Trade;
import com.jpmorgan.exercise.stockmarket.simplestock.calculation.enums.StockType;

public class CalculateYieldPriceGBCE {
	
	@Bean
    Map<String, Stock> getStockMap() {
        HashMap<String, Stock> stockMap = new HashMap<String, Stock>();
        stockMap.put("TEA", new Stock("TEA", StockType.COMMON, 0.0, 0.0, 100.0));
        stockMap.put("POP", new Stock("POP", StockType.COMMON, 8.0, 0.0, 100.0));
        stockMap.put("ALE", new Stock("ALE", StockType.COMMON, 23.0, 0.0, 60.0));
        stockMap.put("GIN", new Stock("GIN", StockType.PREFERRED, 8.0, 0.2, 100.0));
        stockMap.put("JOE", new Stock("JOE", StockType.COMMON, 13.0, 0.0, 250.0));
        return stockMap;
    }
	
	
    public static void main( String[] args )
    {
        try {
            ApplicationContext context = 
                    new AnnotationConfigApplicationContext(CalculateYieldPriceGBCE.class);
            
            @SuppressWarnings("unchecked")
    		Map<String, Stock> stockMap = context.getBean("getStockMap", Map.class);
            for (Stock stock: stockMap.values()) {
            	System.out.println(stock.getStockSymbol() + " dividend yield: " + CalculateYieldPriceGBCEHelper.getDividend(stock, 3.3));
            	System.out.println( stock.getStockSymbol() + " P/E ratio: " + CalculateYieldPriceGBCEHelper.getPERatio(stock, 3.3));
                
            	for (int i=1; i <= 10; i++) {
            		
            		Random random = new Random();
            		Integer rangeMin = 1;
            		Integer rangeMax = 10;
            		double randomValue = CalculateYieldPriceGBCEHelper.getTradePrice() + (rangeMax - rangeMin) * random.nextDouble();
            		
            		TreeMap<Date, Trade> stockTradesBuy = CalculateYieldPriceGBCEHelper.buyStock(i, randomValue);
            		
            		System.out.println( " Time " + stockTradesBuy.lastKey()+ " " + stock.getStockSymbol() + " buy " + i + " shares at " + randomValue);
            		
            		randomValue = CalculateYieldPriceGBCEHelper.getTradePrice() + (rangeMax - rangeMin) * random.nextDouble();

            		TreeMap<Date, Trade> stockTradesSell = CalculateYieldPriceGBCEHelper.sellStock(i, randomValue);
            		System.out.println( " Time " + stockTradesBuy.lastKey() + " " + stock.getStockSymbol() + " sell " + i + " shares at " + randomValue);
            	}
            	
            	
            	System.out.println( stock.getStockSymbol() + " volumeWeightedStockPrice: " + CalculateYieldPriceGBCEHelper.calculateVolumeWeightedStockPrice());
            	System.out.println();
            }
            System.out.println();
            Double GBCEallShareIndex = CalculateYieldPriceGBCEHelper.allShareIndex(stockMap);
            System.out.println( "GBCE All Share Index: " + GBCEallShareIndex);
            
		} catch (Exception e) {
			System.out.println(e.getMessage() + " : " + e);
		}
    }
}
