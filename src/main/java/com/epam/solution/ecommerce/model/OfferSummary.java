package com.epam.solution.ecommerce.model;

import java.util.Objects;

public class OfferSummary {
	
	private String offerProductName;
	
	private String offerSummary;
	
	private Long offerId;
	
	public String getOfferProductName() {
		return offerProductName;
	}

	public void setOfferProductName(String offerProductName) {
		this.offerProductName = offerProductName;
	}

	public String getOfferSummary() {
		return offerSummary;
	}

	public void setOfferSummary(String offerSummary) {
		this.offerSummary = offerSummary;
	}

	public Long getOfferId() {
		return offerId;
	}

	public void setOfferId(Long offerId) {
		this.offerId = offerId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(offerId, offerProductName, offerSummary);
	}

	@Override
	public boolean equals(final Object object) {
		if (this == object)
			return true;
		if (!(object instanceof OfferSummary))
			return false;
		final OfferSummary other = (OfferSummary) object;
		return Objects.equals(offerId, other.offerId) && Objects.equals(offerProductName, other.offerProductName)
				&& Objects.equals(offerSummary, other.offerSummary);
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("OfferSummary [offerProductName=");
		builder.append(offerProductName);
		builder.append(", offerSummary=");
		builder.append(offerSummary);
		builder.append(", offerId=");
		builder.append(offerId);
		builder.append("]");
		return builder.toString();
	}
}
