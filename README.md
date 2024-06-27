# Change_Color_With_JSlider
 RGB Değiştirme Uygulaması
Bu Java uygulaması, RGB renk değerlerini değiştirmenizi ve bu değerleri görsel olarak bir JPanel üzerinde gözlemlemenizi sağlayan bir GUI (Grafik Kullanıcı Arayüzü) programıdır. Uygulama, renkli kaydırıcılar kullanarak bir panelin arka plan rengini dinamik olarak değiştirir.

Özellikler
Kırmızı, Yeşil, Mavi Renk Kontrolleri: Kullanıcılar, RGB renk değerlerini JSlider bileşenleri aracılığıyla ayarlayabilir.
Gerçek Zamanlı Renk Değişimi: Renk kaydırıcılarının hareketine bağlı olarak JPanel'in arka plan rengi anında değişir.
Renk Değerlerini Gösterme: Her renk kaydırıcısının değeri, ilgili JLabel üzerinde gösterilir.
Kullanılan Bileşenler
JFrame: Uygulamanın ana penceresi.
JPanel: Renk değişikliğinin gözlemlendiği ana panel.
JLabel: Renk isimlerini ve değerlerini gösteren etiketler.
JSlider: RGB renk değerlerini ayarlamak için kullanılan kaydırıcılar.
Kod Açıklaması
1. Pencere ve Panel Kurulumu
JFrame frame = new JFrame("RGB Değiştirme");
JPanel GUI = new JPanel();
GUI.setLayout(null);

JPanel jp2 = new JPanel();
jp2.setBackground(Color.blue);
jp2.setSize(295, 120);
jp2.setLocation(0, 0);
jp2.setLayout(null);

GUI.add(jp2);
JFrame: "RGB Değiştirme" başlığıyla bir ana pencere oluşturulur.
JPanel: Renk değişikliklerini görüntülemek için mavi renkli bir panel oluşturulur ve GUI adlı ana panele eklenir.
2. Renk Etiketleri ve Kaydırıcıları
Kırmızı Kaydırıcı:
JLabel kirmizi = new JLabel("Kirmizi:");
kirmizi.setSize(70, 20);
kirmizi.setLocation(200, 125);
GUI.add(kirmizi);

JSlider sliderKirmizi = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
sliderKirmizi.setSize(185, 10);
sliderKirmizi.setLocation(10, 130);
sliderKirmizi.setMaximum(255);
sliderKirmizi.setBackground(Color.RED);
GUI.add(sliderKirmizi);
Yeşil Kaydırıcı:
JLabel yesil = new JLabel("Yeşil:");
yesil.setSize(60, 20);
yesil.setLocation(200, 175);
GUI.add(yesil);

JSlider sliderYesil = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
sliderYesil.setSize(185, 10);
sliderYesil.setLocation(10, 180);
sliderYesil.setMaximum(255);
sliderYesil.setBackground(Color.green);
GUI.add(sliderYesil);

Mavi Kaydırıcı:
JLabel mavi = new JLabel("Mavi:");
mavi.setSize(50, 20);
mavi.setLocation(200, 225);
GUI.add(mavi);

JSlider sliderMavi = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
sliderMavi.setSize(185, 10);
sliderMavi.setLocation(10, 230);
sliderMavi.setMaximum(255);
sliderMavi.setBackground(Color.blue);
GUI.add(sliderMavi);


#include <LiquidCrystal_I2C.h>

#include <Servo.h>
Servo giris,cikis;
LiquidCrystal_I2C lcd(0x27,16,2);

//Akıllı Otopark Projesi
//Giriş Otopark (1)
const int tr_pin1 = 8;
const int ec_pin1 = 6;

int sure1;
int mesafe1;
//Çıkış Otopark (2)
const int tr_pin2 = 13;
const int ec_pin2 = 12;

int sure2;
int mesafe2;

int arabaSayac = 0;

void setup() {
  // initialize LCD
  lcd.init();
  // turn on LCD backlight                      
  lcd.backlight();
  pinMode(tr_pin1 , OUTPUT);
  pinMode(ec_pin1 , INPUT);
  
   pinMode(tr_pin2 , OUTPUT);
  pinMode(ec_pin2 , INPUT);

  giris.attach(10);
  cikis.attach(11);
  Serial.begin(9600);
  
}
void loop() {
int girisMesafesi = girisMesafe();
if(girisMesafesi<10){
  giris.write(90);

  arabaSayac++;
    delay(3000);
  //Kontrol Yeri kod fallback
  Serial.print("ic");
  Serial.println(girisMesafesi);
  Serial.print("arac:");
  Serial.println(arabaSayac);
}
else{
  giris.write(0);
  Serial.print("dıs");
  Serial.println(girisMesafesi);
}

int cikisMesafesi = cikisMesafe();
if(cikisMesafesi<10){
  cikis.write(90);

  arabaSayac--;
    delay(3000);
  //Kontrol Yeri kod fallback
  Serial.print("ic");
  Serial.println(cikisMesafesi);
  Serial.print("arac:");
  Serial.println(arabaSayac);
}
else{
  cikis.write(0);
  Serial.print("dıs");
  Serial.println(cikisMesafesi);
}

if(arabaSayac<0){
  arabaSayac=0;
}
if(arabaSayac==8){
//Otopark Full
  while(1){
    if(cikisMesafe()<10){
      break;
    }
  }
  
}
lcd.setCursor(0,0); // İlk satırın başlangıç noktası
lcd.print("AKILLI OTOPARK"); 

lcd.setCursor(0,1); // İlk satırın başlangıç noktası
lcd.print("Araba Sayisi:"); 

lcd.setCursor(14,1); // İlk satırın başlangıç noktası
lcd.print(arabaSayac); 


}
int girisMesafe(){
  digitalWrite(tr_pin1 , HIGH);
  delayMicroseconds(1000);
  digitalWrite(tr_pin1 , LOW);
  sure1 = pulseIn(ec_pin1 , HIGH);
  mesafe1 = (sure1 / 2) / 29.1;
  return mesafe1;
  
  /*int yakinlik1 = map(mesafe1, 0, 100, 1, 4);
  Serial.print(" cisme olan uzaklık 1= ");
  Serial.println(mesafe1);
  Serial.println("------------------");*/
}
int cikisMesafe(){
  digitalWrite(tr_pin2 , HIGH);
  delayMicroseconds(1000);
  digitalWrite(tr_pin2 , LOW);
  sure2 = pulseIn(ec_pin2 , HIGH);
  mesafe2 = (sure2 / 2) / 29.1;
  return mesafe2;
  /*int yakinlik2 = map(mesafe2, 0, 100, 1, 4);
  Serial.print(" cisme olan uzaklık 2= ");
  Serial.println(mesafe2);
  Serial.println("------------------");*/
}

Bu kodda yapılanları kısaca açıkla README.md dosyası için kullanacağım.
ChatGPT
Elbette! Aşağıda verdiğiniz akıllı otopark projesi için README.md dosyasına ekleyebileceğiniz kısa bir açıklama bulunmaktadır:

Akıllı Otopark Projesi
Bu proje, Arduino kullanarak basit bir akıllı otopark sistemini simüle etmektedir. Proje, araçların giriş ve çıkışlarını kontrol ederek otoparktaki araç sayısını otomatik olarak saymakta ve bu bilgiyi bir LCD ekranda göstermektedir. Ayrıca, otopark dolduğunda giriş kapısını kapalı tutarak yeni araçların girmesini engellemektedir.

Özellikler
Araç Sayma: Ultrasonik sensörler kullanarak araçların giriş ve çıkışlarını algılar.
Servo Motor Kontrolü: Araç girişi ve çıkışı sırasında servo motorları kullanarak bariyerlerin açılıp kapanmasını sağlar.
LCD Ekran: Otoparktaki araç sayısını 16x2 LCD ekranda gösterir.
Otopark Dolu Uyarısı: Otopark kapasitesi dolduğunda yeni araçların girmesini engeller ve çıkış yapılana kadar bekler.
Bağlantı ve Pinler
Giriş Sensörü:
Trig Pin: 8
Echo Pin: 6
Çıkış Sensörü:
Trig Pin: 13
Echo Pin: 12
Servo Motorlar:
Giriş Servo: Pin 10
Çıkış Servo: Pin 11
LCD Ekran: I2C bağlantısı ile 0x27 adresinde 16x2 LCD ekran
Kod Açıklaması
Başlatma: LCD ekranı başlatır ve geri aydınlatmasını açar. Sensörlerin ve servo motorların pinlerini tanımlar.

Giriş ve Çıkış Kontrolü:

Araç giriş sensörü (tr_pin1 ve ec_pin1) aracılığıyla mesafeyi ölçer. Mesafe 10 cm'den az olduğunda giriş servo motorunu (giris) 90 derece açar ve araç sayacını bir arttırır.
Araç çıkış sensörü (tr_pin2 ve ec_pin2) aracılığıyla mesafeyi ölçer. Mesafe 10 cm'den az olduğunda çıkış servo motorunu (cikis) 90 derece açar ve araç sayacını bir azaltır.
Araç Sayısı: Araba sayısını 8 (otopark kapasitesi) üzerinde tutmaz, böylece kapasite dolduğunda yeni araç girişi engellenir.

LCD Ekran: Otoparktaki toplam araç sayısını ve "AKILLI OTOPARK" mesajını LCD ekranda gösterir.

Fonksiyonlar: girisMesafe() ve cikisMesafe() fonksiyonları, ultrasonik sensörlerden mesafe verilerini okur ve hesaplar.

Bu açıklama, projenizin temel işleyişini ve işlevlerini özetler. README dosyanıza ekleyerek projenizi daha anlaşılır kılabilirsiniz!

int echoPin=9;
int trigPin=8;
int led[]= {2,3,4,5};
int buzzer= 6;
void setup() {
Serial.begin(9600);
pinMode(trigPin,OUTPUT);
pinMode(echoPin,INPUT);
pinMode(2,OUTPUT);
pinMode(3,OUTPUT);
pinMode(4,OUTPUT);
pinMode(5,OUTPUT);
pinMode(buzzer,OUTPUT);
}

void loop() {
int sure,mesafe,yakinlik;
digitalWrite(trigPin,LOW);
delayMicroseconds(2);
digitalWrite(trigPin,HIGH);
delayMicroseconds(10);
digitalWrite(trigPin,LOW);

sure = pulseIn(echoPin,HIGH);
mesafe = (sure/2)/29.1;
Serial.print("mesafe: ");
Serial.println(mesafe);
yakinlik = map(mesafe,0,100,1,4);
Serial.print("yakinlik: ");
Serial.println(yakinlik);
for(int i= 0;i<4;i++){
  digitalWrite(led[i],LOW);
}
for(int i = 0;i<yakinlik;i++){
  digitalWrite(led[i],HIGH);
}
  digitalWrite(buzzer,HIGH);
  delay(100*yakinlik);
  digitalWrite(buzzer,LOW);
  delay (100*yakinlik);
 
}

aynı şekilde bu kod içinde yaz
ChatGPT
Tabii! İşte verdiğiniz kod için README.md dosyasına ekleyebileceğiniz kısa bir açıklama:

Ultrasonik Mesafe Sensörü ile Yakınlık Uyarı Sistemi
Bu proje, Arduino ve ultrasonik sensör kullanarak nesnelerin mesafesini ölçen ve mesafeye göre LED'leri ve bir buzzer'ı kontrol eden bir yakınlık uyarı sistemidir. Bu sistem, bir nesne yaklaştıkça LED'leri kademeli olarak yakar ve buzzer'ı artan sürelerde aktif eder.

Özellikler
Ultrasonik Mesafe Ölçümü: Nesne ile sensör arasındaki mesafeyi hesaplar.
LED Göstergesi: Nesneye olan uzaklığa göre 4 LED'in durumunu kontrol eder.
Buzzer Uyarısı: Nesne yaklaştıkça buzzer'ın yanıp sönme süresini kısaltır.
Mesafe Gösterimi: Seri monitörde ölçülen mesafeyi ve yakınlık seviyesini gösterir.
Bağlantı ve Pinler
Ultrasonik Sensör:
Trig Pin: 8
Echo Pin: 9
LED'ler:
LED1: Pin 2
LED2: Pin 3
LED3: Pin 4
LED4: Pin 5
Buzzer: Pin 6
Kod Açıklaması
Başlatma: Seri haberleşmeyi başlatır ve sensör, LED'ler ve buzzer için gerekli pin modlarını tanımlar.

Mesafe Ölçümü:

Trig pinini kısa süreliğine düşük ve ardından yüksek yaparak ultrasonik sensörün bir sinyal göndermesini sağlar.
Echo pininde sinyalin geri dönme süresini ölçer (pulseIn fonksiyonu kullanılarak).
Mesafeyi hesaplar: Mesafe = (Süre / 2) / 29.1 (cm cinsinden).
Yakınlık Belirleme:

map fonksiyonu ile ölçülen mesafeyi 1 ile 4 arasında bir yakınlık seviyesine dönüştürür.
Yakınlık seviyesi, nesnenin mesafesine göre belirlenir ve bu seviye 4 LED'in durumunu kontrol eder.
LED ve Buzzer Kontrolü:

Tüm LED'leri kapatır.
Yakınlık seviyesine göre belirli sayıda LED'i açar.
Buzzer'ı yakar ve belirli bir süre sonra kapatır. Bu süre, yakınlık seviyesine göre değişir.
Mesafe ve Yakınlık Bilgisi:

Ölçülen mesafe ve yakınlık seviyesi seri monitörde gösterilir.
Kullanım
Arduino kartınıza uygun bağlantıları yapın:

Trig ve Echo pinlerini ultrasonik sensörünüze bağlayın.
LED'leri belirlenen pinlere bağlayın.
Buzzer'ı belirtilen pinlere bağlayın.
Arduino'yu bilgisayarınıza bağlayın ve kodu yükleyin.

Seri monitörde mesafe ve yakınlık seviyelerini izleyebilir ve nesne yaklaştıkça LED'lerin yanıp söndüğünü gözlemleyebilirsiniz.

Örnek Bağlantı Şeması
mathematica
Kodu kopyala
LED1 - Pin 2
LED2 - Pin 3
LED3 - Pin 4
LED4 - Pin 5
Buzzer - Pin 6
Ultrasonik Sensör Trig - Pin 8
Ultrasonik Sensör Echo - Pin 9
Bu açıklama, projenizin temel işlevlerini ve nasıl çalıştığını özetler. README dosyanıza ekleyerek projenizi daha anlaşılır hale getirebilirsiniz!

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RGBDegistirme extends JFrame {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("RGB Değiştirme");

		JPanel GUI = new JPanel();
		GUI.setLayout(null);

		JPanel jp2 = new JPanel();
		jp2.setBackground(Color.blue);
		jp2.setSize(295, 120);
		jp2.setLocation(0, 0);
		jp2.setLayout(null);

		GUI.add(jp2);
		
		
		JLabel kirmizi = new JLabel("Kirmizi:");
		kirmizi.setSize(70, 20);
		kirmizi.setLocation(200, 125);
		GUI.add(kirmizi);

		JSlider sliderKirmizi = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		sliderKirmizi.setSize(185, 10);
		sliderKirmizi.setLocation(10, 130);
		sliderKirmizi.setMaximum(255);
		sliderKirmizi.setBackground(Color.RED);

		GUI.add(sliderKirmizi);

		JLabel yesil = new JLabel("Yeşil:");
		yesil.setSize(60, 20);
		yesil.setLocation(200, 175);
		GUI.add(yesil);
		
		
		JSlider sliderYesil = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		sliderYesil.setSize(185, 10);
		sliderYesil.setLocation(10, 180);
		sliderYesil.setMaximum(255);
		sliderYesil.setBackground(Color.green);
		GUI.add(sliderYesil);

		JLabel mavi = new JLabel("Mavi:");
		mavi.setSize(50, 20);
		mavi.setLocation(200, 225);
		GUI.add(mavi);

		JSlider sliderMavi = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		sliderMavi.setSize(185, 10);
		sliderMavi.setLocation(10, 230);
		sliderMavi.setMaximum(255);
		sliderMavi.setBackground(Color.blue);
		GUI.add(sliderMavi);

		
		// RGB Değişim Olayı
				sliderKirmizi.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent a) {

						jp2.setBackground(new Color(sliderKirmizi.getValue(), sliderYesil.getValue(), sliderMavi.getValue()));
						kirmizi.setText("Kırmızı:" + sliderKirmizi.getValue());
					}

				});
				sliderYesil.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent a) {

						jp2.setBackground(new Color(sliderKirmizi.getValue(), sliderYesil.getValue(), sliderMavi.getValue()));
						yesil.setText("Yeşil:" + sliderYesil.getValue());
					}

				});

				sliderMavi.addChangeListener(new ChangeListener() {
					
					public void stateChanged(ChangeEvent a ) {
						
						jp2.setBackground(new Color(sliderKirmizi.getValue(), sliderYesil.getValue(), sliderMavi.getValue()));
						mavi.setText("Mavi:"+sliderMavi.getValue());
					}
					
				});

		
		
		
		frame.setVisible(true);
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(GUI);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	}

}

aynı şekilde bu java kodu içinde yap
ChatGPT
Tabii! Aşağıda verdiğiniz Java kodu için README.md dosyasına ekleyebileceğiniz kısa bir açıklama bulunmaktadır:

RGB Değiştirme Uygulaması
Bu Java uygulaması, RGB renk değerlerini değiştirmenizi ve bu değerleri görsel olarak bir JPanel üzerinde gözlemlemenizi sağlayan bir GUI (Grafik Kullanıcı Arayüzü) programıdır. Uygulama, renkli kaydırıcılar kullanarak bir panelin arka plan rengini dinamik olarak değiştirir.

Özellikler
Kırmızı, Yeşil, Mavi Renk Kontrolleri: Kullanıcılar, RGB renk değerlerini JSlider bileşenleri aracılığıyla ayarlayabilir.
Gerçek Zamanlı Renk Değişimi: Renk kaydırıcılarının hareketine bağlı olarak JPanel'in arka plan rengi anında değişir.
Renk Değerlerini Gösterme: Her renk kaydırıcısının değeri, ilgili JLabel üzerinde gösterilir.
Kullanılan Bileşenler
JFrame: Uygulamanın ana penceresi.
JPanel: Renk değişikliğinin gözlemlendiği ana panel.
JLabel: Renk isimlerini ve değerlerini gösteren etiketler.
JSlider: RGB renk değerlerini ayarlamak için kullanılan kaydırıcılar.
Kod Açıklaması
1. Pencere ve Panel Kurulumu
java
Kodu kopyala
JFrame frame = new JFrame("RGB Değiştirme");
JPanel GUI = new JPanel();
GUI.setLayout(null);

JPanel jp2 = new JPanel();
jp2.setBackground(Color.blue);
jp2.setSize(295, 120);
jp2.setLocation(0, 0);
jp2.setLayout(null);

GUI.add(jp2);
JFrame: "RGB Değiştirme" başlığıyla bir ana pencere oluşturulur.
JPanel: Renk değişikliklerini görüntülemek için mavi renkli bir panel oluşturulur ve GUI adlı ana panele eklenir.
2. Renk Etiketleri ve Kaydırıcıları
Kırmızı Kaydırıcı:
java
Kodu kopyala
JLabel kirmizi = new JLabel("Kirmizi:");
kirmizi.setSize(70, 20);
kirmizi.setLocation(200, 125);
GUI.add(kirmizi);

JSlider sliderKirmizi = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
sliderKirmizi.setSize(185, 10);
sliderKirmizi.setLocation(10, 130);
sliderKirmizi.setMaximum(255);
sliderKirmizi.setBackground(Color.RED);
GUI.add(sliderKirmizi);
Yeşil Kaydırıcı:
java
Kodu kopyala
JLabel yesil = new JLabel("Yeşil:");
yesil.setSize(60, 20);
yesil.setLocation(200, 175);
GUI.add(yesil);

JSlider sliderYesil = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
sliderYesil.setSize(185, 10);
sliderYesil.setLocation(10, 180);
sliderYesil.setMaximum(255);
sliderYesil.setBackground(Color.green);
GUI.add(sliderYesil);
Mavi Kaydırıcı:
java
Kodu kopyala
JLabel mavi = new JLabel("Mavi:");
mavi.setSize(50, 20);
mavi.setLocation(200, 225);
GUI.add(mavi);

JSlider sliderMavi = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
sliderMavi.setSize(185, 10);
sliderMavi.setLocation(10, 230);
sliderMavi.setMaximum(255);
sliderMavi.setBackground(Color.blue);
GUI.add(sliderMavi);
3. Renk Değişim Olayı
Her kaydırıcı, ChangeListener kullanarak dinlenir ve kaydırıcının değerine göre panelin arka plan rengi güncellenir.
sliderKirmizi.addChangeListener(new ChangeListener() {
    public void stateChanged(ChangeEvent a) {
        jp2.setBackground(new Color(sliderKirmizi.getValue(), sliderYesil.getValue(), sliderMavi.getValue()));
        kirmizi.setText("Kırmızı:" + sliderKirmizi.getValue());
    }
});

sliderYesil.addChangeListener(new ChangeListener() {
    public void stateChanged(ChangeEvent a) {
        jp2.setBackground(new Color(sliderKirmizi.getValue(), sliderYesil.getValue(), sliderMavi.getValue()));
        yesil.setText("Yeşil:" + sliderYesil.getValue());
    }
});

sliderMavi.addChangeListener(new ChangeListener() {
    public void stateChanged(ChangeEvent a) {
        jp2.setBackground(new Color(sliderKirmizi.getValue(), sliderYesil.getValue(), sliderMavi.getValue()));
        mavi.setText("Mavi:" + sliderMavi.getValue());
    }
});

Kullanım
Uygulamayı Çalıştırın: Kodunuzu çalıştırarak uygulamayı başlatın.
RGB Değerlerini Ayarlayın: Kaydırıcıları hareket ettirerek panelin arka plan rengini değiştirin.
Renkleri Gözlemleyin: Kaydırıcıların değerine göre panelin rengini anında gözlemleyin.
