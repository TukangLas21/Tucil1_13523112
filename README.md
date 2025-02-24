# IQ Puzzler Pro Solver - Tugas Kecil 1 IF2211 Strategi Algoritma

## Deskripsi Singkat
IQ Puzzler Pro merupakan suatu permainan untuk mengasah kemampuan berpikir dan kreativitas. Tujuan utama dari permainan ini adalah pemain harus mengisi seluruh papan dengan piece atau blok yang tersedia. Pada implementasi ini, papan permainan selalu dimulai dengan keadaan kosong, setiap piece dapat dirotasikan dan dicerminkan, serta permainan dinyatakan selesai jika dan hanya jika semua piece sudah diletak dan papan permainan terisi penuh. <br>
Algoritma utama dalam program ini menggunakan pendekatan _brute force_ dengan mencari kombinasi posisi dan orientasi piece sehingga dapat ditemukan solusi. Bila semua kombinasi telah dicoba dan tidak ada yang memenuhi kondisi penyelesaian permainan, program akan memberikan pesan bahwa tidak ada solusi yang dapat ditampilkan.

## Persyaratan Sistem
Sebelum menjalankan program, pastikan Anda telah menginstalasi Java Development Kit (JDK) untuk menjalankan program berbasis Java ini. Anda dapat mengunduh JDK melalui [pranala](https://www.oracle.com/in/java/technologies/downloads/#java23) ini.

## Instalasi / Memulai
 Silakan clone repositori ini dengan menjalankan perintah di bawah ini:
```sh
git clone https://github.com/TukangLas21/Tucil1_13523112.git
cd Tucil1_13523112
```

### Menjalankan Program
Program perlu dikompilasi terlebih dahulu sebelum dijalankan. Silakan menjalankan perintah di bawah ini untuk mengompilasi program. Pastikan bahwa Anda sudah berada di folder Tucil1_13523112.
```sh
cd src
javac -d ../bin Game/*.java Utils/*.java Main.java
```
Setelah mengompilasi, silakan menjalankan perintah di bawah ini untuk menjalankan program.
```sh
cd ../bin
java Main
```

## Author
Aria Judhistira (13523112)
