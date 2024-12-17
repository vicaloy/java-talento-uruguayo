package persistencia;

import java.io.*;
import valueObjects.VOCapaLogica;

public class PersistenciaDatos {
	public void respaldar(String nomArch, VOCapaLogica p) {
		try {
			FileOutputStream f = new FileOutputStream(nomArch);
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(p);
			o.close();
			f.close();
		} catch (IOException e) {
		}

	}

	public VOCapaLogica recuperar(String nomArch) {
		try {
			FileInputStream f = new FileInputStream(nomArch);
			ObjectInputStream o = new ObjectInputStream(f);
			VOCapaLogica p = (VOCapaLogica) o.readObject();
			o.close();
			f.close();
			return p;
		} catch (IOException | ClassNotFoundException e) {
			return null;
		}
	}
}