package lab5.task4;

public class BadAlbum extends Album {

	boolean isPalindrome(int n) {
		int reverse = 0, aux = n, u;
		while (n > 0) {
			u = n % 10;
			reverse = (reverse * 10) + u;
			n = n / 10;
		}
		return aux == reverse;
	}

	@Override
	public void addSong(Song song) {
		if (song.getName().length() == 3 && isPalindrome(song.getId()))
			getListSongs().add(song);
	}
}
