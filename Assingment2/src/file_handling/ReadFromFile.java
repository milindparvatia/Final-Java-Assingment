//????   6 I  file_handling/ReadFromFile  java/lang/Object <init> ()V Code
//  	   LineNumberTable LocalVariableTable this Lfile_handling/ReadFromFile; main ([Ljava/lang/String;)V  java/io/File  myFiles/file1.txt
//     (Ljava/lang/String;)V  java/util/Scanner
//     (Ljava/io/File;)V	    java/lang/System   ! out Ljava/io/PrintStream;
//  # $ % nextLine ()Ljava/lang/String;
// ' ) ( java/io/PrintStream *  println
//  , - . hasNextLine ()Z
//  0 1  close
// 3 5 4 java/lang/Throwable 6 7 
//addSuppressed (Ljava/lang/Throwable;)V
// 9 ; : java/io/FileNotFoundException < % 
//getMessage args [Ljava/lang/String; file1 Ljava/io/File; input Ljava/util/Scanner; e Ljava/io/FileNotFoundException; 
//StackMapTable > 
//SourceFile ReadFromFile.java !               /     *? ?    
//                 
//   	      #     j? Y? LMN? Y+? :? ? ? "? &? +???? 9? /? 1M? ? /,?N,? -M? 
//,-? ,-? 2,?M? ,? 8? &?   . ;    H H   
// ^ ^ 9  
//   "    	 
//     
// &  .  _  i     *    j = >   
// ` ? @   . A B  _ 
// C D  E   7 	?   F  3 3   
//T 3? 
//A 3		?   F   9
//  G    H