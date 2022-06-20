import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceGrafica extends JFrame implements ActionListener {
        //Atributos para a interface Grafica
        private JButton botaoINICIAR;
        private JButton botaoPARAR;
        private JLabel LabelNumerosGerados;
        private JLabel LabelNumerosProcessados;
        private JLabel LabelRelogio;
        static private JTextArea listaNumerosGerados;
        static private JTextArea listaNumerosProcessados;
        private JScrollPane scrollListaNumerosGerados;
        private JScrollPane scrollListaNumerosProcessados;
        private Container janelaPrincipal;

        //Atributos para criar as threads
         ThreadProdutor objetoThreadProdutor; //thread que vai gerar os numeros
         ThreadConsumidor objetoThreadConsumidor; //thread que vai processar os numeros

        //Construtor da classe InterfaceGrafica
        public InterfaceGrafica(){
                setSize(350,640); //Tamanho da janela principal
                setTitle("Threads - Relogio"); //Titulo da janela principal
                janelaPrincipal = getContentPane(); //pegar uma referência para a janela principal
                janelaPrincipal.setLayout(null); //limpartodo o conteúdo da janela

                //criar os conponentes da interface grafica
                botaoINICIAR = new JButton("Iniciar");
                botaoPARAR = new JButton("Parar");
                LabelRelogio = new JLabel("Relogio");
                LabelNumerosGerados = new JLabel("Gerados");
                LabelNumerosProcessados = new JLabel("Processados");
                listaNumerosGerados = new JTextArea();
                listaNumerosProcessados = new JTextArea();
                scrollListaNumerosGerados = new JScrollPane(listaNumerosGerados);
                scrollListaNumerosProcessados = new JScrollPane(listaNumerosProcessados);

                //configurar o posicionamento dos componentes na tela
                botaoINICIAR.setBounds(70,550,80,40); //deslocamento na tela: coluna, linha - comprimento, altura
                botaoPARAR.setBounds(160,550,80,40);
                LabelNumerosGerados.setBounds(50,3,100,20);
                LabelNumerosProcessados.setBounds(180,3,100,20);
                scrollListaNumerosGerados.setBounds(30,20,100,500);
                scrollListaNumerosProcessados.setBounds(160,20,100,500);
                LabelRelogio.setBounds(120,520,80,40);

                //adcionar os componentes na tela
                janelaPrincipal.add(botaoINICIAR);
                janelaPrincipal.add(botaoPARAR);
                janelaPrincipal.add(LabelNumerosGerados);
                janelaPrincipal.add(LabelNumerosProcessados);
                janelaPrincipal.add(scrollListaNumerosGerados);
                janelaPrincipal.add(scrollListaNumerosProcessados);
                janelaPrincipal.add(LabelRelogio);

                //fazer com que todos os componentes fiquem visiveis na tela
                setVisible(true);

                //inserir tratamento dos eventos para os botoes INICIAR e PARAR
                botaoINICIAR.addActionListener(this);
                botaoPARAR.addActionListener(this);

                //inserir um relogio na tela;
                Temporizador reloginho = new Temporizador(LabelRelogio);
        }

        @Override
        public void  actionPerformed(ActionEvent e){

                //Verificar se o usuario clicou no botao "Iniciar"
                if(e.getActionCommand().equals("Iniciar")){
                        //limpar o conteúdo das listas
                        listaNumerosGerados.setText("");
                        listaNumerosProcessados.setText("");
                        //criando as Theads Produtor e Consumidor
                        objetoThreadProdutor = new ThreadProdutor(listaNumerosGerados);
                        objetoThreadConsumidor = new ThreadConsumidor(objetoThreadProdutor, listaNumerosProcessados);
                }

                //Verificar se o usuario clicou no botao "Parar"
                if(e.getActionCommand().equals("Parar")){

                        //setar o atributo "Encerrar" para true --> fazer as Trheads encerrarem o Loop infinito
                        objetoThreadProdutor.Encerrar = true;
                        objetoThreadConsumidor.Encerrar = true;
                }
        }

        public static void main(String[] args) {
                //programa principal ---> chamar a interface Grafica
                InterfaceGrafica tela = new InterfaceGrafica();
        }
}
