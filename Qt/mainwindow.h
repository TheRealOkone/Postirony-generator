
#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QPixmap>
#include <QString>
#include <QBuffer>
#include <QScreen>
#include <QFile>
#include <QDebug>
#include <QFileDialog>
#include <QDir>
#include <fstream>
#include <QPalette>
#include <QStyle>
#include <QThread>
#include <QSettings>
#include <QTimer>
#include <QMenu>
#include <QMouseEvent>
#include <QGraphicsWidget>
#include <QStyleFactory>
#include <QToolButton>

using namespace std;

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT


    enum MouseType {
            None = 0,
            Move
        };
public:
    explicit MainWindow(QWidget *parent = nullptr);
    ~MainWindow();
    bool eventFilter(QObject *watched, QEvent *event);

    void closeEvent(QCloseEvent *event);

    void otsenka();




private slots:
    void on_pushButton_clicked();

    void on_action_triggered(bool checked);

    void on_action_2_triggered(bool checked);

    void on_pushButton_2_clicked();

    void on_pushButton_3_clicked();

    void on_toolButton_clicked(bool checked);

    void on_pushButton_4_clicked();

    void on_pushButton_5_clicked();

    void on_pushButton_6_clicked();

    void on_pushButton_7_clicked();

    void on_action_3_triggered();

public slots:
    void setState();

    void setPreviousPosition(QPoint previousPosition);


protected:
    void mousePressEvent(QMouseEvent *event);
    void mouseReleaseEvent(QMouseEvent *event);
    void mouseMoveEvent(QMouseEvent *event);


private:
    MouseType m_leftMouseButtonPressed;
    QPoint m_previousPosition;
    MouseType checkResizableField(QMouseEvent *event);




public:
    Ui::MainWindow *ui;
};

class Main_Window : public MainWindow
{
public:
    explicit Main_Window(QWidget *parent = nullptr);
    void closeEvent(QCloseEvent *event);
};

#endif // MAINWINDOW_H
