package lab5.task4;

public class DangerousAlbum extends Album {

	boolean isPrime(int n) {
		if (n == 2)
			return true;
		if (n % 2 == 0 || n == 1)
			return false;
		for (int div = 3; div * div <= n; div = div + 2) {
			if (n % div == 0)
				return false;
		}
		return  true;
	}

	@Override
	public void addSong(Song song) {
		if (isPrime(song.getId()))
			getListSongs().add(song);
	}
}
