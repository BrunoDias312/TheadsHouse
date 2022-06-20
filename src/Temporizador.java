import java.util.Timer;
import javax.swing.JLabel;
public class Temporizador extends Timer{
    private Timer timer;
    private Relogio relogioNaTela;

    public Temporizador(JLabel labelInterface){
        timer = new Timer();
        relogioNaTela = new Relogio(labelInterface);
        timer.schedule(relogioNaTela,0, 1000);
    }
}
