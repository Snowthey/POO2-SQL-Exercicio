import java.awt.Container;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class InterfaceGrafica extends JFrame implements ActionListener{
    ConexaoBancoDeDados objBancoDeDados;

    private JButton btnInserir;
    private JButton btnRemover;
    private JButton btnAlterar;
    private JButton btnPesquisar;

    private JLabel lblNome;
    private JLabel lblEndereco;
    private JLabel lblTelefone;
    private JLabel lblResultadoPesquisa;
    private JLabel lblMensagem;
    private JLabel lblId;

    private JTextField txtNome;
    private JTextField txtEndereco;
    private JTextField txtTelefone;
    private JTextField txtId;

    private JTextArea listaPesquisaBancoDeDados;
    private JScrollPane scrollPesquisaBancoDedados;

    private Container janelaprincipal;

    public InterfaceGrafica(){
        setSize(900,370);
        setTitle("Conexão com banco de daddos");
        janelaprincipal = getContentPane();
        janelaprincipal.setLayout(null);

        txtNome = new JTextField();
        txtEndereco = new JTextField();
        txtTelefone = new JTextField();
        txtId = new JTextField();

        btnInserir = new JButton("Inserir");
        btnRemover = new JButton("Remover");
        btnAlterar = new JButton("Alterar");
        btnPesquisar = new JButton("Pesquisar");

        lblMensagem = new JLabel(" ----");
        lblNome = new JLabel("Nome");
        lblId = new JLabel("ID");
        lblEndereco = new JLabel("Endereço");
        lblTelefone = new JLabel("Telefone");
        lblResultadoPesquisa = new JLabel("Resultado da Pesquisa no Bando de Dados");

        listaPesquisaBancoDeDados = new JTextArea();
        scrollPesquisaBancoDedados = new JScrollPane(listaPesquisaBancoDeDados);

        btnInserir.setBounds(10,215,90,40);
        btnRemover.setBounds(102,280,90,40);
        btnAlterar.setBounds(10,280,90,40);
        btnPesquisar.setBounds(102,215,95,40);

        lblMensagem.setBounds(50,170,250,20);
        lblNome.setBounds(10,40,80,20);
        lblEndereco.setBounds(10,85,100,20);
        lblTelefone.setBounds(10,130,100,20);
        lblResultadoPesquisa.setBounds(480,5,300,20);
        lblId.setBounds(220,285,30,30);
        scrollPesquisaBancoDedados.setBounds(420,25,450,235);

        txtNome.setBounds(100,40,200,25);
        txtEndereco.setBounds(100,85,200,25);
        txtTelefone.setBounds(100,130,200,25);
        txtId.setBounds(240,285,30,30);

        janelaprincipal.add(btnInserir);
        janelaprincipal.add(btnRemover);
        janelaprincipal.add(btnAlterar);
        janelaprincipal.add(btnPesquisar);
        janelaprincipal.add(lblEndereco);
        janelaprincipal.add(lblNome);
        janelaprincipal.add(lblTelefone);
        janelaprincipal.add(lblResultadoPesquisa);
        janelaprincipal.add(scrollPesquisaBancoDedados);
        janelaprincipal.add(txtNome);
        janelaprincipal.add(txtEndereco);
        janelaprincipal.add(txtTelefone);
        janelaprincipal.add(lblMensagem);
        janelaprincipal.add(lblId);
        janelaprincipal.add(txtId);

        setVisible(true);

        btnInserir.addActionListener(this);
        btnRemover.addActionListener(this);
        btnAlterar.addActionListener(this);
        btnPesquisar.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("Inserir")){
            Pessoa objeto = new Pessoa(txtNome.getText(), txtEndereco.getText(), txtTelefone.getText());

            try{
                ConexaoBancoDeDados objBancoDeDados = new ConexaoBancoDeDados();
                String mensagem = objBancoDeDados.InserirDados(objeto);
                lblMensagem.setText(mensagem);
            } catch (SQLException e1){
                e1.printStackTrace();
            }
        }

        if(e.getActionCommand().equals("Remover")){
            Pessoa objeto = new Pessoa(txtNome.getText(), txtEndereco.getText(), txtTelefone.getText());

            try{
                ConexaoBancoDeDados objBancoDeDados = new ConexaoBancoDeDados();
                int id = Integer.parseInt(txtId.getText());
                String mensagem = objBancoDeDados.RemoverDados(id);
                lblMensagem.setText(mensagem);
            } catch (SQLException e1){
                e1.printStackTrace();
            }
        }

        if(e.getActionCommand().equals("Alterar")){
            Pessoa objeto = new Pessoa(txtNome.getText(), txtEndereco.getText(), txtTelefone.getText());

            try{
                ConexaoBancoDeDados objBancoDeDados = new ConexaoBancoDeDados();
                int id = Integer.parseInt(txtId.getText());
                String mensagem = objBancoDeDados.AlterarDados(objeto, id);
                lblMensagem.setText(mensagem);
            } catch (SQLException e1){
                e1.printStackTrace();
            }
        }

        if(e.getActionCommand().equals("Pesquisar")){
            try {
                ConexaoBancoDeDados objBancoDeDados = new ConexaoBancoDeDados();
                ArrayList<String> relatorioBancoDeDados = objBancoDeDados.Relatorio();

                listaPesquisaBancoDeDados.setText("");
                for(String texto : relatorioBancoDeDados){
                    listaPesquisaBancoDeDados.append(texto + "\n\n");
                }
            } catch (SQLException e1){
                e1.printStackTrace();
            }

        }
    }
}
