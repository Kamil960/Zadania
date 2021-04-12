
package ZadanieII;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Zadanie12 extends JFrame implements ActionListener
{
    JLabel [] label;
    JRadioButton[] radio;
    JButton[] button;
    JTextField[] field;
    String wynik  = "0";
    ButtonGroup group;
    double netto, brutto, vat;
    int procent;
    Zadanie12(String tytul)
    {
        super(tytul);
    }
    public static void main(String[] args)
    {
        Zadanie12 okno = new Zadanie12("Kalkulator Vat");
        okno.init();
        okno.setVisible(true);
    }
    void init()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450,200);
        setResizable(false);
        //Ustawiam rokład główny okna
        setLayout(new GridLayout(4,1));
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        add(p1);
        add(p2);
        add(p3);
        add(p4);
        //Ustawiam rokład poszeczególnych części okna
        p1.setLayout(new FlowLayout());
        p2.setLayout(new FlowLayout());
        p3.setLayout(new GridLayout(1,4));
        p4.setLayout(new FlowLayout());
        //Implementuje RadioButtony
        radio = new JRadioButton[4];
        radio[0] = new JRadioButton("0%", true);
        radio[1] = new JRadioButton("5%");
        radio[2] = new JRadioButton("8%");
        radio[3] = new JRadioButton("23%");
        //Grupuje RadioButtony
        group = new ButtonGroup();
        for (int i = 0; i < radio.length; i++) group.add(radio[i]);
        //Implementuje Labele
        label = new JLabel[7];
        label[0] = new JLabel("stawki Vat: ");
        label[1] = new JLabel("Wartość netto");
        label[2] = new JLabel("zł");
        label[3] = new JLabel("Wartość podatku ");
        label[4] = new JLabel(wynik+"zł");
        label[5] = new JLabel("Wartość brutto");
        label[6] = new JLabel("zł");
        //Implementuje Buttony
        button = new JButton[2];
        button[0] = new JButton("OBLICZ");
        button[1] = new JButton("OBLICZ");
        //Przypisuje zdarzenie do każdego Buttona
        button[0].addActionListener(this);
        button[1].addActionListener(this);
        //Stylizuje Buttony
        button[0].setForeground(Color.red);
        button[1].setForeground(Color.blue);
        //Implementuje Fieldy
        field = new JTextField[2];
        field[0] = new JTextField();
        field[1] = new JTextField();
        //Stylizuje fieldy
        field[0].setHorizontalAlignment(SwingConstants.RIGHT);
        field[1].setHorizontalAlignment(SwingConstants.RIGHT);
        field[0].setColumns(10);
        field[1].setColumns(10);
        //Blokuje w Fieldach wpisywanie nie oczekiwanych znaków
        field[0].addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '.' && field[0].getText().indexOf('.') > -1) 
                {
                    e.consume();
                    return;
                }
                if (e.getKeyChar() == '-' && field[0].getText().indexOf('-') > -1) 
                {
                    e.consume();
                    return;
                }
                if (!((e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || e.getKeyChar() == '.'))
                    e.consume();
            }
        });
        field[1].addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '.' && field[1].getText().indexOf('.') > -1) 
                {
                    e.consume();
                    return;
                }
                if (e.getKeyChar() == '-' && field[1].getText().indexOf('-') > -1) 
                {
                    e.consume();
                    return;
                }
                if (!((e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || e.getKeyChar() == '.'))
                    e.consume();
            }
        });
        //Dodaje elementy do pierwszej części okna
        p1.add(label[0]);
        for (int i = 0; i < radio.length; i++) p1.add(radio[i]);
        //Dodaje elementy do drugiej części okna
        p2.add(label[1]);
        p2.add(field[0]);
        p2.add(label[2]);
        p2.add(button[0]);
        //Dodaje elementy do trzeciej części okna
        p3.add(new JPanel());
        p3.add(label[3]);
        p3.add(label[4]);
        p3.add(new JPanel());
        
        //Dodaje elementy do czwartej części okna
        p4.add(label[5]);
        p4.add(field[1]);
        p4.add(label[6]);
        p4.add(button[1]);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Wybrany();
        Object source = e.getSource();
        if(source == button[0])
        {
            if(field[0].getText() == null)
                return;
            else
            {
                netto = Double.parseDouble(field[0].getText());
                vat = netto * procent/100;
                label[4].setText(Double.toString(vat)+"zł"); 
            }
        }
        else if(source == button[1])
        {
            if(field[1].getText() == null)
                return;
            else
            {
                brutto = Double.parseDouble(field[1].getText());
                vat = brutto * procent/(100+procent);
                wynik = Double.toString(vat);
                if(wynik.indexOf('.') < wynik.length()-2)
                    wynik = wynik.substring(0, wynik.indexOf('.')+3);
                System.out.println(wynik.indexOf('.')+3);
                label[4].setText(wynik+"zł"); 
            }
        }
    }
    public void Wybrany()
    {
         //Sprawdzam który procent jest wybrany
        if(radio[0].isSelected()==true)
            procent = 0;
        else if(radio[1].isSelected()==true)
            procent = 5;
        else if(radio[2].isSelected()==true)
            procent = 8;
        else if(radio[3].isSelected()==true)
            procent = 23;
    }
    
}
