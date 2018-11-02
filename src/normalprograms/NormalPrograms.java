/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package normalprograms;

/**
 *
 * @author Shri
 */
import java.util.*;
import java.util.regex.*;
import java.io.*;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class NormalPrograms extends Application{
	private final double W = 800, H = 600;
	private TextField tf;
	private TextArea ta;
        private int count = -1;
        private HashMap<String, String> morse = new HashMap<>();
        
        private HashMap<String, String> getMorse(){
            HashMap<String, String> map = new HashMap<>();
            map.put("a", "._");
            map.put("b", "_...");
            map.put("c", "_._.");
            map.put("d", "_..");
            map.put("e", ".");
            map.put("f", ".._.");
            map.put("g", "__.");
            map.put("h", "....");
            map.put("i", "..");
            map.put("j", ".___");
            map.put("k", "_._");
            map.put("l", "._..");
            map.put("m", "__");
            map.put("n", "_.");
            map.put("o", "___");
            map.put("p", ".__.");
            map.put("q", "__._");
            map.put("r", "._.");
            map.put("s", "...");
            map.put("t", "_");
            map.put("u", ".._");
            map.put("v", "..._");
            map.put("w", ".__");
            map.put("x", "_.._");
            map.put("y", "_.__");
            map.put("z", "__..");
            map.put(" ", "|");
            return map;
        }
        
        private <K, V> K getKey(Map<K, V> map, V value){
            return map.entrySet()
                        .stream()
                        .filter(p -> value.equals(p.getValue()))
                        .map(Map.Entry::getKey)
                        .findFirst().get();
        }

	private Parent getContent(){
		Pane pane = new Pane();
		Button bt = new Button();
                Button convert = new Button("Convert");
		HBox hb = new HBox();
		String[] cString = new String[]{"MorseCode", "Text"};
		Label label = new Label();
                Label countV = new Label();
                

		tf = new TextField();
		ta = new TextArea();
                morse = getMorse();
                

		label.setText(cString[0]);
		bt.setText("<-->");
                tf.setFont(Font.font("Arial Black", FontWeight.EXTRA_BOLD, 30));
		ta.setLayoutY(100);
                ta.setFont(Font.font("Impact", FontWeight.EXTRA_BOLD, 30));

		bt.setOnAction(e -> {
			if(label.getText().equals(cString[0]))label.setText(cString[1]);
			else if(label.getText().equals(cString[1]))label.setText(cString[0]);
		});
                
                convert.setOnAction(e -> {
                    ta.setText("");
                    if(label.getText().equals(cString[0])){
                        String ss = tf.getText().toLowerCase();
                        String[] s = ss.split("");
                        for(String st : s)
                            ta.appendText(morse.get(st));
                    }else{
                        String[] s = tf.getText().split(" ");
                        //ta.appendText(tf.getText());
                        for(String sr : s)
                            ta.appendText(getKey(morse, sr));
                        
                    }
                });
                
//                tf.setOnKeyPressed(e -> {
//                    
//                    if(e.getCode() != KeyCode.SHIFT){
//                        if(e.getCode() == KeyCode.BACK_SPACE){
//                            if(tf.getText().length() < 2){
//                                count = -1;
//                            }else{
//                                count--;
//                            }
//                        }else{
//                            count++;
//                        }
//                    }
//                    
//                    
//                    countV.setText("Count = "+count);
//                });
//		
//		tf.textProperty().addListener(c -> {
//                    String ss = tf.getText().toLowerCase();
//                    String[] s = ss.split("");
//                    if(tf.getText().equals(" "))ta.appendText(" ");
//                    if(tf.getText().equals("\b"))ta.appendText("BackSpace");
//                    if(count > -1 && morse.containsKey(s[count])){
//                        ta.appendText(morse.get(s[count]));
//                    }
//                    
//                });

		hb.setSpacing(10);
		hb.getChildren().addAll(tf, bt, label, countV, convert);
		pane.getChildren().addAll(hb, ta);
		return pane;
	}

	public void start(Stage stage) throws Exception{
		stage.setTitle("MorseCode To Text and Vice-Versa");
		stage.setScene(new Scene(getContent(), W, H));
		stage.show();
	}
	public static void main(String[] args) {launch(args);}
}

