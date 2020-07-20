package model;

import java.util.Comparator;

public class PostSale extends Post {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5039149176858787217L;
	private float asking_price;
	private float highest_offer;
	private float minimum_raise;

	public PostSale(String id, String title, String description, Student creatorID, boolean status, String imageURL,
					float asking_price, float highest_offer, float minimum_raise) {
		super(id, title, description, creatorID, status, imageURL);

		this.setAsking_price(asking_price);
		this.minimum_raise = minimum_raise;
		this.highest_offer = highest_offer;

	}

	public PostSale() {
		// TODO Auto-generated constructor stub
	}

	public float getAsking_price() {
		return asking_price;
	}

	public void setAsking_price(float asking_price) {
		this.asking_price = asking_price;
	}

	public float getHighest_offer() {
		return highest_offer;
	}

	public void setHighest_offer(float highest_offer) {
		this.highest_offer = highest_offer;
	}

	public float getMinimum_raise() {
		return minimum_raise;
	}

	public void setMinimum_raise(float minimum_raise) {
		this.minimum_raise = minimum_raise;
	}

	@Override
	public boolean handleReply(Reply reply) throws InputErorrException {

		boolean check = false;

		if (reply.getValue() > getHighest_offer()) {
			if (reply.getValue() >= getMinimum_raise() + getHighest_offer()) {

				for (int i = 0; i < reply.getPostID().getReplies().size(); i++) {
					if (reply.getPostID().getReplies().get(i).getRespondersId().getSid()
							.equals(reply.getRespondersId().getSid())) {
						throw new InputErorrException("\nRequest denied! you have already submited your offer");
					}
				}
			} else
				throw new InputErorrException("\nRequest denied! you have to raise offer with minimum raise given");
		} else
			throw new InputErorrException(
					"\nRequest denied! your offer should be greater than Highest Offer available");

		reply.getPostID().getReplies().add(reply);

		setHighest_offer(reply.getValue());

		check = true;

		return check;
	}

	@Override
	public String getReplyDetails() {
		String replies = "";

		getReplies().sort(Comparator.comparing(Reply::getValue).reversed());

		for (int i = 0; i < getReplies().size(); i++) {
			replies += getReplies().get(i).getRespondersId().getSid() + "\t$";
			replies += getReplies().get(i).getValue();

			if (getReplies().size() != i + 1) {
				replies += "\n";
			}
		}

		if (replies.equals("")) {
			replies = "No Offers yet";
		}
		return replies;
	}

}
