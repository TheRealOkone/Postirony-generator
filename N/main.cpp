#include "mainwindow.h"

#include <QApplication>
#include <QStyleFactory>
#include <QtConcurrent/QtConcurrent>



int main(int argc, char *argv[])
{
    setlocale(LC_ALL, "rus");
    QApplication a(argc, argv);
    MainWindow w;

    qApp->setStyle(QStyleFactory::create("Fusion"));
    w.show();




    return a.exec();
}
