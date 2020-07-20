package model;

import java.util.Comparator;

public class PostJob extends Post {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4480641187362573438L;

	private float propose_price;
	private float lowest_offer;

	public PostJob(String id, String title, String description, Student creatorID, boolean status, String imageURL,
				   float propose_price, float lowest_offer) {
		super(id, title, description, creatorID, status, imageURL);
		// TODO Auto-generated constructor stub
		this.propose_price = propose_price;
		this.lowest_offer = lowest_offer;
	}

	public PostJob(String id, String title, String description, Student imageURL, String student, boolean status, float propose_price, float lowest_offer) {
		super();
	}

	public float getPropose_price() {
		return propose_price;
	}

	public void setPropose_price(float propose_price) {
		this.propose_price = propose_price;
	}

	public float getLowest_offer() {
		return lowest_offer;
	}

	public void setLowest_offer(float f) {
		this.lowest_offer = f;
	}

	@Override
	public boolean handleReply(Reply reply) throws InputErorrException {

		boolean check = false;

		if (reply.getValue() <= getPropose_price()) {
			if (reply.getPostID().getStatus().equals("OPEN")) {

				for (int i = 0; i < reply.getPostID().getReplies().size(); i++) {
					if (reply.getPostID().getReplies().get(i).getRespondersId().getSid()
							.equals(reply.getRespondersId().getSid())) {
						throw new InputErorrException("\nRequest denied! you have already submited your offer");
					}
				}

				if (getLowest_offer() == 0 || reply.getValue() < getLowest_offer()) {
					reply.getPostID().getReplies().add(reply);

					setLowest_offer(reply.getValue());

					check = true;
				} else {
					throw new InputErorrException(
							"\nRequest denied! your offer should be less than Lowest offer available");
				}
			} else
				throw new InputErorrException("\nRequest denied! post status is closed");
		} else
			throw new InputErorrException("\nRequest denied! you have to submit offer less than Propose price");
		return check;
	}

	@Override
	public String getReplyDetails() {

		String replies = "";

		getReplies().sort(Comparator.comparing(Reply::getValue));

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
