package ZadanieII;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Zadanie8 extends JFrame implements ActionListener
{
    public static JButton[] tab;
   public static JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,b19,b20;
    JTextField f1;
    String minus;
    String pole = null;
    double x, y=0;
    double wynik;
    int operacyjny = -1;
    public static void main(String[] args)
    {
        
        Zadanie8 okno = new Zadanie8("Kalkulator");
        okno.init();  
        okno.setVisible(true);
    }
    Zadanie8(String tytul)
    {
        super(tytul);
    }
    void init()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,400);
        setResizable(false);
      
      setLayout(new GridLayout(3,1));
      JPanel p1 = new JPanel();
      add(p1);
      JPanel p2 = new JPanel();
      add(p2);
      JPanel p3 = new JPanel();
      add(p3);
      p1.setLayout(new GridLayout(3,1));
      p1.add(new JPanel());
      f1 = new JTextField();
      p1.add(f1, BorderLayout.CENTER);
      
      f1.setHorizontalAlignment(SwingConstants.RIGHT);
      f1.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '.' && f1.getText().indexOf('.') > -1) 
                {
                    e.consume();
                    return;
                }
                if (e.getKeyChar() == '-' && f1.getText().indexOf('-') > -1) 
                {
                    e.consume();
                    return;
                }
                if (!((e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || e.getKeyChar() == '.'))
                    e.consume();
            }
        });
        
        p1.add(new JPanel());
        p2.setLayout(new GridLayout(2,5));
        tab = new JButton[20];

        b1 = new JButton("1");
        b1.setName("1");
        p2.add(b1);
        b1.addActionListener(this);
        b2 = new JButton("2");
        b2.setName("2");
        p2.add(b2);
        b2.addActionListener(this);
        b3 = new JButton("3");
        b3.setName("3");
        p2.add(b3);
        b3.addActionListener(this);
        b4 = new JButton("+");
        p2.add(b4);
        b4.addActionListener(this);
        b5 = new JButton("^");
        p2.add(b5);
        b5.addActionListener(this);
        b6 = new JButton("4");
        b6.setName("4");
        p2.add(b6);
        b6.addActionListener(this);
        b7 = new JButton("5");
        b7.setName("5");
        p2.add(b7);
        b7.addActionListener(this);
        b8 = new JButton("6");
        b8.setName("6");
        p2.add(b8);
        b8.addActionListener(this);
        b9 = new JButton("-");
        p2.add(b9);
        b9.addActionListener(this);
        b10 = new JButton("-/");
        p2.add(b10);
        b10.addActionListener(this);
        p3.setLayout(new GridLayout(2,5));
        b11 = new JButton("7");
        b11.setName("7");
        p3.add(b11);
        b11.addActionListener(this);
        b12 = new JButton("8");
        b12.setName("8");
        p3.add(b12);
        b12.addActionListener(this);
        b13 = new JButton("9");
        b13.setName("9");
        p3.add(b13);
        b13.addActionListener(this);
        b14 = new JButton("/");
        p3.add(b14);
        b14.addActionListener(this);
        b15 = new JButton("%");
        p3.add(b15);
        b15.addActionListener(this);
        b20 = new JButton("+/-");
        p3.add(b20);
        b20.addActionListener(this);
        b16 = new JButton("0");
        b16.setName("0");
        p3.add(b16);
        b16.addActionListener(this);
        b17 = new JButton("=");
        p3.add(b17);
        b17.addActionListener(this);
        b18 = new JButton(".");
        b18.setName(".");
        p3.add(b18);
        b18.addActionListener(this);
        b19 = new JButton("C");
        p3.add(b19);
        b19.addActionListener(this);
    }
   
    public void actionPerformed (ActionEvent zdarzenie)
    {
        Object zrodlo = zdarzenie.getSource(); 
        if(b1 == zrodlo)
            PrzyciskLiczbowy(b1);
        if(b2 == zrodlo)
            PrzyciskLiczbowy(b2);
        if(b3 == zrodlo)
            PrzyciskLiczbowy(b3);
        
        if(b4 == zrodlo)
            PrzyciskOperacyjny(b4);
        if(b5 == zrodlo)
            PrzyciskOperacyjny(b5);
        
        if(b6 == zrodlo)
            PrzyciskLiczbowy(b6);
        if(b7 == zrodlo)
            PrzyciskLiczbowy(b7);
        if(b8 == zrodlo)
            PrzyciskLiczbowy(b8);
        
        if(b9 == zrodlo)
            PrzyciskOperacyjny(b9);
        if(b10 == zrodlo)
            PrzyciskOperacyjny(b10);
        
        if(b11 == zrodlo)
            PrzyciskLiczbowy(b11);
        if(b12 == zrodlo)
            PrzyciskLiczbowy(b12);
        if(b13 == zrodlo)
            PrzyciskLiczbowy(b13);
        
        if(b14 == zrodlo)
            PrzyciskOperacyjny(b14);
        if(b15 == zrodlo)
            PrzyciskOperacyjny(b15);
        
         if(b16 == zrodlo)
            PrzyciskZerowy(b16);
         
         if(b17 == zrodlo)
             PrzyciskWynikowy(b17);
         if(b18 == zrodlo)
            Kropka(b18);
         if(b19 == zrodlo)
         {
             pole = null;
             f1.setText(pole);
         }
         if(b20 == zrodlo)
             PlusMinus(b20);
         
    }
    
    double Dzielenie(double x, double y) throws DzieleniePrzezZero
    {
        if(y == 0) throw new DzieleniePrzezZero();
        return x / y;
    }
    public void PlusMinus(JButton b)
    {
            minus = "-";
            if(pole == null)
                return;
            else if(f1.getText().indexOf('-') <= -1 )
             {
                 f1.setText(minus+=f1.getText());
             }
             else
                 f1.setText(pole);
    }
    public void PrzyciskLiczbowy(JButton b)
    {
            String liczba = b.getName();
            if(pole == null)
            {
                pole = liczba;
                f1.setText(pole);
            }
            else
            {
                    pole += liczba;
                    f1.setText(pole); 
            }    
        }
    public void PrzyciskOperacyjny(JButton b)
    {
        if(b == b4)
        {
            operacyjny = 0;
            x = Double.valueOf(f1.getText());
            pole = null;
        }
        else if(b == b5)
        {
            operacyjny = 1;
            x = Double.valueOf(f1.getText());
            pole = null;
        }
        else if(b == b9)
        {
            operacyjny = 2;
            x = Double.valueOf(f1.getText());
            pole = null;
        }
        else if(b == b10)
        {
           operacyjny = 3;
           x = Double.valueOf(f1.getText());
           wynik = Math.sqrt(x);
           f1.setText(Double.toString(wynik));
           pole = null;
        }
        else if(b == b14)
        {
           operacyjny = 4;
           x = Double.valueOf(f1.getText());
           pole = null;
        }
        else if(b == b15)
        {
           operacyjny = 5;
           x = Double.valueOf(f1.getText());
           pole = null;
        }
    }
    public void Kropka(JButton b)
    {
            String liczba = b.getName();
            if(f1.getText().indexOf('.') > -1)
                return;
            else
                f1.setText(pole += liczba); 
    }
     public void PrzyciskZerowy(JButton b)
    {
            String liczba = b.getName();
            if(pole == null)
              return;
            else
            {
                pole += liczba;
                f1.setText(pole); 
            } 
    }
    public void PrzyciskWynikowy(JButton b)
    {
        if(operacyjny == -1)
           f1.setText(pole);
        y = Double.valueOf(f1.getText());
        if(operacyjny == 0)
        {
            wynik = x + y;
            f1.setText(Double.toString(wynik));
            pole = null;
        }
       
        if(operacyjny == 1)
        {
            wynik = Math.pow(x,y);
            f1.setText(Double.toString(wynik));
            pole = null;
        }
        else if(operacyjny == 2)
        {
            wynik = x - y;
            f1.setText(Double.toString(wynik));
            pole = null;
        }
        else if(operacyjny == 4)
        {
            try{
               wynik = Dzielenie(x, y); 
            }
            catch(DzieleniePrzezZero k)
            {
                JOptionPane.showMessageDialog(null, "Niedozwolone dzielenie przez zero");
            }
            f1.setText(Double.toString(wynik));
            pole = null;
        }
        else if(operacyjny == 5)
        {
            wynik = x % y;
            f1.setText(Double.toString(wynik));
            pole = null;
        }
    }
}