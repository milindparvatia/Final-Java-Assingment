package model;

public class PostEvent extends Post {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3685814417481222196L;
	private String venue;
	private String date;
	private int capacity;
	private int attendee_count;

	public PostEvent(String id, String title, String description, Student creatorID, boolean status, String imageURL,
					 String venue, String date,
			int capacity, int attendee_count) {
		super(id, title, description, creatorID,  status, imageURL);

		this.capacity = capacity;
		this.date = date;
		this.venue = venue;
		this.attendee_count = attendee_count;
	}

	public PostEvent() {
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getAttendee_count() {
		return attendee_count;
	}

	public void setAttendee_count(int attendee_count) {
		this.attendee_count = attendee_count;
	}

	@Override
	public boolean handleReply(Reply reply) throws InputErorrException {

		boolean check = false;

		if (getAttendee_count() < getCapacity()) {

			for (int i = 0; i < reply.getPostID().getReplies().size(); i++) {
				if (reply.getPostID().getReplies().get(i).getRespondersId().getSid()
						.equals(reply.getRespondersId().getSid())) {
					throw new InputErorrException("\nRequest denied! you have already submited your offer");
				}
			}

			reply.getPostID().getReplies().add(reply);

			setAttendee_count(getAttendee_count() + 1);

			if (getAttendee_count() == getCapacity()) {
				reply.getPostID().setStatus(false);
			}

			check = true;
		} else
			throw new InputErorrException("\nRequest denied! this event has no seat left");

		return check;
	}

	@Override
	public String getReplyDetails() {
		String replies = "";

		for (int i = 0; i < getReplies().size(); i++) {
			replies += getReplies().get(i).getRespondersId().getSid();

			if (getReplies().size() != i + 1) {
				replies += ", ";
			}
		}

		if (replies.equals("")) {
			replies = "No Attendees yet";
		}
		return replies;
	}
}
