package lab5.task4;

import java.util.ArrayList;

public abstract class Album {
	private ArrayList<Song> listSongs = new ArrayList<Song>();

	public ArrayList<Song> getListSongs() {
		return listSongs;
	}
	public void setListSongs(ArrayList<Song> listSongs) {
		this.listSongs = listSongs;
	}

	abstract void addSong(Song song);

	void removeSong() {
		listSongs.remove(0);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Song s : listSongs) {
			sb.append(s.getName());
			sb.append("		");
		}
		return "Lista de melodii din album:" + "\n"
				+ sb + " " + "\n";
	}
}
