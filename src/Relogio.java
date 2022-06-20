import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import javax.swing.*;
public class Relogio extends TimerTask{
    JLabel interfacegrafica;
    Relogio(JLabel label){
        interfacegrafica = label;
    }

    @Override
    public void run(){
        try{//formatar a hora em HORA:MINUTOS:SEGUNDOS
            DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime localTime = LocalTime.now();//pegar o horario atual do sistema
            interfacegrafica.setText(formatoHora.format(localTime));//mostar na tela
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
