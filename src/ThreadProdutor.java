import java.lang.Thread;
import java.util.concurrent.Semaphore;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;

public class ThreadProdutor extends Thread {
    ArrayList<Integer> buffercircular = new ArrayList<Integer>(100);
    int valor = 0;
    public Semaphore Mutex = new Semaphore(1); // Mutual Exclusion
    public boolean Encerrar = false;
    JTextArea ListaInterfaceGrafica;
    Thread objetoThreadProdutor;

    ThreadProdutor(JTextArea lista) {
        //inicializar os atributos
        ListaInterfaceGrafica = lista;
        //criar a thread
        objetoThreadProdutor = new Thread(this);
        //iniciar a thread
        objetoThreadProdutor.start(); // o metodo Run será executado
    }

    public void run() {
        while (Encerrar == false) {//obs: "Encerar será false quando o usuario clicar no botao "parar" na interface
            try {
                //********************REGIAO CRITICA --- Verificar o Mutex
                Mutex.acquire();

                if (buffercircular.size() < 100) {
                    buffercircular.add(valor); //adicionar o valor aquisicionado buffer circular
                    ListaInterfaceGrafica.append(Integer.toString(valor) + "\n"); //mostar o valor na interface
                    valor++;
                }
                //*********LIBERAR O ACESSO A REGIÃO CRITICA**************
                Mutex.release();
                Thread.sleep(10);//fazer a Thread esperar 10 milisegundos para continuar a execução
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}