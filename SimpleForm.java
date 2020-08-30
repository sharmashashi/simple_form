import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.awt.Dimension;

public class SimpleForm {
    static JFrame _frame;
    static JLabel _nameLabel;
    static JTextField _nameField;
    static JLabel _genderLabel;
    static JLabel _education;
    static JButton _append;
    static JRadioButton _male;
    static JRadioButton _female;
    static ButtonGroup _gender;
    static JComboBox<String> _educationDropDown;
    static HashMap<Integer, String> _dropDownItems;

    static void _initWindow() {
        _frame = new JFrame();
        _frame.setSize(500, 500);
        _frame.setDefaultCloseOperation(3);
        _frame.setResizable(false);
        _frame.setLayout(null);
    }

    static void _loadComponents() {
        _nameLabel = new JLabel("Name");
        _nameField = new JTextField();
        _genderLabel = new JLabel("Sex");
        _education = new JLabel("Education");
        _append = new JButton("Append to file");
        _male = new JRadioButton("Male");
        _female = new JRadioButton("female");
        _dropDownItems = new HashMap<>();
        _dropDownItems.put(1, "SEE");
        _dropDownItems.put(2, "+2");
        _dropDownItems.put(3, "BScCSIT");
        _dropDownItems.put(4, "MScCSIT");
        _educationDropDown = new JComboBox<>();
        for (int i = 0; i < 5; i++) {
            _educationDropDown.addItem(_dropDownItems.get(i));
        }
        _gender = new ButtonGroup();
        _gender.add(_male);
        _gender.add(_female);

        //
        _nameLabel.setBounds(10, 10, 100, 50);
        _nameField.setBounds(70, 10, 350, 50);
        _genderLabel.setBounds(10, 70, 100, 50);
        _male.setBounds(120, 70, 100, 50);
        _female.setBounds(220, 70, 100, 50);
        _education.setBounds(10, 130, 100, 50);
        _educationDropDown.setBounds(120, 130, 100, 50);
        _educationDropDown.setPreferredSize(new Dimension(200, 50));
        _educationDropDown.setSelectedIndex(1);
        _append.setBounds(10, 190, 480, 50);
        _append.addActionListener(new ButtonEvent(_male, _female, _nameField, _educationDropDown));
        //
        _frame.add(_nameLabel);
        _frame.add(_nameField);
        _frame.add(_genderLabel);
        _frame.add(_male);
        _frame.add(_female);
        _frame.add(_education);
        _frame.add(_educationDropDown);
        _frame.add(_append);

    }

    public static void main(String[] args) {
        _initWindow();
        _loadComponents();
        _frame.setVisible(true);
    }
}

class ButtonEvent implements ActionListener {
    JRadioButton male;
    JRadioButton female;
    JTextField name;
    JComboBox<String> education;

    ButtonEvent(JRadioButton m, JRadioButton f, JTextField field, JComboBox<String> dd) {
        male = m;
        female = f;
        name = field;
        education = dd;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String gender;
        String _name;
        String _education;

        gender = male.isEnabled() ? "Male" : "Female";
        _name = name.getText();
        _education = (String) education.getSelectedItem();

        File _file = new File("Customer_form.tct");
        try {
            FileWriter _writer = new FileWriter(_file);
            _writer.write(_name + "," + gender + "," + _education);
            _writer.close();
            System.exit(0);

        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}