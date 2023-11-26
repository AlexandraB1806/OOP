package lab5.task4;

public class ThrillerAlbum extends Album {

	@Override
	public void addSong(Song song) {
		if (song.getComposer().equals("Michael Jackson") && song.getId() % 2 == 0)
			getListSongs().add(song);
	}
}
