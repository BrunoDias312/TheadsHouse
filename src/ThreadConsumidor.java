import javax.swing.JTextArea;
import java.lang.Thread;

public class ThreadConsumidor extends Thread {
    ThreadProdutor objetoThreadProdutor; //objeto para acessar a Thread
    Thread objetoThreadConsumidor;
    JTextArea ListaInterfaceGrafica;
    public boolean Encerrar = false;

    ThreadConsumidor(ThreadProdutor temp, JTextArea lista) {
        //inicializar os atributos
        objetoThreadProdutor = temp;
        ListaInterfaceGrafica = lista;
        //criar a thread
        objetoThreadConsumidor = new Thread(this);
        //iniciar a thread (--> executar o metodo run())
        objetoThreadConsumidor.start();
    }

    public void run() {
        while (Encerrar == false) {//obs: "Encerar será false quando o usuario clicar no botao "parar" na interface
            try {//*******REGIAO CRITICA --- Verificar o Mutex
                objetoThreadProdutor.Mutex.acquire();

                if (objetoThreadProdutor.buffercircular.size() > 0) { //Verificar se tem dados no buffer para ser lido
                    int temp = objetoThreadProdutor.buffercircular.remove(0);//remove o primeiro elemento da lista
                    int processamento = temp * 100;//processar dados
                    ListaInterfaceGrafica.append(Integer.toString(processamento) + "\n"); //mostrar o processamento interface
                }

                //**********LIBERAR O ACESSOA REGIÃO CRITICA**********
                objetoThreadProdutor.Mutex.release();
                Thread.sleep(10); //Fazer a thread esperar 10 milisegundos para continuar a execução

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}