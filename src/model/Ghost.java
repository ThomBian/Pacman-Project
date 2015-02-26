package model;

public class Ghost extends Entity {
	
	private Content lastContent;

	public Ghost(Tile position) {
		super(position, Content.GHOST);
		lastContent = Content.EMPTY;
	}

	public Content getLastContent() {
		return lastContent;
	}

	public void setLastContent(Content lastContent) {
		this.lastContent = lastContent;
	}
}
