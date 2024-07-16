package PaooGame.Exeptioooons;

public class TimeExeption extends Exception { //exceptie pentru cand timpul in joc devine negativ
    public TimeExeption(String err)
    {
        super(err);
    }
}
