
import java.util.ArrayList;

public class SistemaCaballeros {

    private ArrayList<Caballero> caballeros;

    public SistemaCaballeros() {
        caballeros = new ArrayList<>();
        predefinir();
    }

    public void ordenarPorIdBurbuja() {
        Caballero aux;

        for (int i = 0; i < caballeros.size() - 1; i++) {
            for (int j = i + 1; j < caballeros.size(); j++) {

                if (caballeros.get(i).getId() >
                        caballeros.get(j).getId()) {

                    aux = caballeros.get(i);
                    caballeros.set(i, caballeros.get(j));
                    caballeros.set(j, aux);
                }
            }
        }
    }

    public int buscarIndicePorId(int id) {

        int i = 0;
        int s = caballeros.size() - 1;
        int c;

        if (id <= 0) {
            return -1;
        }

        while (i <= s) {

            c = (i + s) / 2;

            if (caballeros.get(c).getId() == id) {
                return c;
            }

            if (id < caballeros.get(c).getId()) {
                s = c - 1;
            } else {
                i = c + 1;
            }
        }

        return -1;
    }

    public void validarDatos(Caballero caballero) {

        if (caballero == null) {
            throw new IllegalArgumentException(
                    "El caballero no es valido");
        }

        if (caballero.getId() <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser mayor a 0");
        }

        if (caballero.getNombre() == null ||
                caballero.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El nombre es obligatorio");
        }

        if (!caballero.getRango().equals("Bronce") &&
                !caballero.getRango().equals("Plata") &&
                !caballero.getRango().equals("Oro")) {
            throw new IllegalArgumentException(
                    "El rango no es valido");
        }

        if (caballero.getConstelacion() == null ||
                caballero.getConstelacion().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "La constelacion es obligatoria");
        }

        if (caballero.getNivelPoder() < 1 ||
                caballero.getNivelPoder() > 10) {
            throw new IllegalArgumentException(
                    "El poder debe estar entre 1 y 10");
        }

        if (caballero.getMision() == null ||
                caballero.getMision().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "La mision es obligatoria");
        }

        if (caballero.getDificultad() < 1 ||
                caballero.getDificultad() > 5) {
            throw new IllegalArgumentException(
                    "La dificultad debe estar entre 1 y 5");
        }

        if (Double.isNaN(caballero.getRecompensa()) ||
                Double.isInfinite(caballero.getRecompensa()) ||
                caballero.getRecompensa() < 0) {
            throw new IllegalArgumentException(
                    "La recompensa no es valida");
        }
    }

    public void registrar(Caballero caballero) {

        validarDatos(caballero);

        if (buscarIndicePorId(caballero.getId()) != -1) {
            throw new IllegalArgumentException(
                    "Ya existe un caballero con ese ID");
        }

        caballeros.add(caballero);
        ordenarPorIdBurbuja();
    }

    public Caballero buscarCaballero(int id) {

        int posicion = buscarIndicePorId(id);

        if (posicion == -1) {
            throw new IllegalArgumentException(
                    "No existe un caballero con ese ID");
        }

        return caballeros.get(posicion);
    }

    public void modificarCaballero(int id, String nombre,
                                   String rango, String constelacion,
                                   int poder, String mision,
                                   int dificultad, double recompensa) {

        Caballero actual = buscarCaballero(id);

        Caballero nuevo = new Caballero(
                id, nombre, rango, constelacion,
                poder, mision, dificultad, recompensa
        );

        validarDatos(nuevo);

        actual.setNombre(nombre);
        actual.setRango(rango);
        actual.setConstelacion(constelacion);
        actual.setNivelPoder(poder);
        actual.setMision(mision);
        actual.setDificultad(dificultad);
        actual.setRecompensa(recompensa);
    }

    public ArrayList<Caballero> listarCaballeros() {
        return new ArrayList<>(caballeros);
    }

    public int cantidadCaballeros() {
        return caballeros.size();
    }
    public void predefinir() {

        registrar(new Caballero(1, "Seiya", "Bronce", "Pegaso",
                8, "Proteger el Santuario", 3, 80000));

        registrar(new Caballero(2, "Shiryu", "Plata", "Dragon",
                9, "Defender a Atena", 4, 150000));

        registrar(new Caballero(3, "Hyoga", "Bronce", "Cisne",
                8, "Rescatar aldeanos", 3, 200000));

        registrar(new Caballero(4, "Shun", "Plata", "Andromeda",
                9, "Recuperar la armadura", 4, 300000));

        registrar(new Caballero(5, "Mu", "Oro", "Aries",
                10, "Proteger las doce casas", 5, 500000));
    }
}

