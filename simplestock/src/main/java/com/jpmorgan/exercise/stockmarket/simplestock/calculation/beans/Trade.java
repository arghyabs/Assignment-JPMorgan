package com.jpmorgan.exercise.stockmarket.simplestock.calculation.beans;

import com.jpmorgan.exercise.stockmarket.simplestock.calculation.enums.TradeType;

public class Trade {
	
	private TradeType tradeType;
	private Integer shareQuantity;
	private Double tradePrice;

	public TradeType getTradeType() {
		return tradeType;
	}

	public void setTradeType(TradeType tradeType) {
		this.tradeType = tradeType;
	}

	public Integer getShareQuantity() {
		return shareQuantity;
	}

	public void setShareQuantity(Integer shareQuantity) {
		this.shareQuantity = shareQuantity;
	}

	public Double getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(Double tradePrice) {
		this.tradePrice = tradePrice;
	}

	public Trade(TradeType type, Integer quantity, Double price) {
		this.setTradeType(type);
		this.setShareQuantity(quantity);
		this.setTradePrice(price);
	}

	@Override
	public String toString() {
		return "Trade --> tradeType=" + tradeType + ", shareQuantity=" + shareQuantity + ", tradePrice="
				+ tradePrice;
	}
}