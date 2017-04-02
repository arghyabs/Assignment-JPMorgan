package com.jpmorgan.exercise.stockmarket.simplestock.calculation;

import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.jpmorgan.exercise.stockmarket.simplestock.calculation.beans.Stock;
import com.jpmorgan.exercise.stockmarket.simplestock.calculation.beans.Trade;
import com.jpmorgan.exercise.stockmarket.simplestock.calculation.enums.TradeType;

public class CalculateYieldPriceGBCEHelper {
	
	private static TreeMap<Date, Trade> stockTrades =new TreeMap<Date, Trade>();;
	
	public static Double getDividend(Stock stock, Double price) {
		switch(stock.getStockType()) {
			case COMMON:
				return stock.getStockLastDividend()/price;
			case PREFERRED:
				return stock.getStockFixedDividend() * stock.getStockParValue()/price;
			default:
				return 0.0;
		}
	}
	
	public static Double getPERatio(Stock stock, Double price) {
		return price/(stock.getStockLastDividend());
	}
	
	public static TreeMap<Date, Trade> buyStock(Integer quantity, Double price) {
	Trade trade = new Trade(TradeType.BUY, quantity, price);
	stockTrades.put(new Date(), trade);
	return stockTrades;
	}
	
	public static TreeMap<Date, Trade> sellStock(Integer quantity, Double price) {
		Trade trade = new Trade(TradeType.SELL, quantity, price);
		stockTrades.put(new Date(), trade);	
		return stockTrades;
	}
	
	public static Double calculateVolumeWeightedStockPrice() {
		Date now = new Date();
		
		Date startTime = new Date(now.getTime() - (15 * 60 * 1000));
	
		SortedMap<Date, Trade> trades = stockTrades.tailMap(startTime);
		
		Double volumeWeigthedStockPrice = 0.0;
		Integer totalQuantity = 0;
		for (Trade trade: trades.values()) {
			totalQuantity += trade.getShareQuantity();
			volumeWeigthedStockPrice += trade.getTradePrice() * trade.getShareQuantity();
		}
		return volumeWeigthedStockPrice/totalQuantity;
	}
	
	public static Double getTradePrice() {
		if (stockTrades.size() > 0) {
			return stockTrades.lastEntry().getValue().getTradePrice();
		} else {
			return 0.0;
		}
	}
	
	public static Double allShareIndex(Map<String, Stock> stocks) {
		Double allShareIndex = 0.0;
		for(Stock stock: stocks.values()) {
			allShareIndex+=CalculateYieldPriceGBCEHelper.getTradePrice();
		}
		return Math.pow(allShareIndex, 1.0 / stocks.size());
	}

}
