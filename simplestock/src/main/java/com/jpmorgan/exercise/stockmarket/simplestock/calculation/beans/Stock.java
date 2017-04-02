package com.jpmorgan.exercise.stockmarket.simplestock.calculation.beans;

import com.jpmorgan.exercise.stockmarket.simplestock.calculation.enums.StockType;

public class Stock {
	
	private String stockSymbol;
	private StockType stockType;
	private Double stockLastDividend;
	private Double stockFixedDividend;
	private Double stockParValue;
	
	public Stock(String symbol, StockType type, Double lastDividend, Double fixedDividend, Double parValue) {
		this.setStockSymbol(symbol);
		this.setStockType(type);
		this.setStockLastDividend(lastDividend);
		this.setStockFixedDividend(fixedDividend);
		this.setStockParValue(parValue);
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public StockType getStockType() {
		return stockType;
	}

	public void setStockType(StockType stockType) {
		this.stockType = stockType;
	}

	public Double getStockLastDividend() {
		return stockLastDividend;
	}

	public void setStockLastDividend(Double stockLastDividend) {
		this.stockLastDividend = stockLastDividend;
	}

	public Double getStockFixedDividend() {
		return stockFixedDividend;
	}

	public void setStockFixedDividend(Double stockFixedDividend) {
		this.stockFixedDividend = stockFixedDividend;
	}

	public Double getStockParValue() {
		return stockParValue;
	}

	public void setStockParValue(Double stockParValue) {
		this.stockParValue = stockParValue;
	}

	@Override
	public String toString() {
		return "Stock --> symbol=" + stockSymbol + 
				", type=" + stockType + 
				", lastDividend=" + stockLastDividend + 
				", fixedDividend=" + stockFixedDividend + 
				", parValue=" + stockParValue;
	}
}
