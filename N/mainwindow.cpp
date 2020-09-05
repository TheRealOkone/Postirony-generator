#include "mainwindow.h"
#include "ui_mainwindow.h"
#include "stylehelper.h"

bool check_1 = false;
bool check_2;
bool dark_ = false;
bool avtors = false;
QString line = "C:\\";
QString line1;
QString mark;
static const QString greenSS = QString("color: white;border-radius: %1;background-color: qlineargradient(spread:pad, x1:0.145, y1:0.16, x2:1, y2:1, stop:0 rgba(20, 252, 7, 255));").arg(7/2);
static const QString redSS = QString("color: white;border-radius: %1;background-color: qlineargradient(spread:pad, x1:0.145, y1:0.16, x2:0.92, y2:0.988636, stop:0 rgba(255, 12, 12, 255));").arg(7/2);


Main_Window::Main_Window(QWidget* parent) :
    MainWindow (parent) {}
void Main_Window::closeEvent(QCloseEvent *event)
{
     avtors = false;
}


MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{

    ui->setupUi(this);
    ui->groupBox->setVisible(false);
    ui->groupBox_2->setVisible(false);
    ui->groupBox_3->setVisible(false);
    ui->label->installEventFilter(this);
    this->setMouseTracking(true);

    ui->toolButton->addAction(ui->action);
    ui->toolButton->addAction(ui->action_2);
    ui->toolButton->addAction(ui->action_3);
    ui->pushButton->setIcon(QIcon(QDir::currentPath() + "\\qt.jpg"));

    QSettings lf("my", "prog");
    dark_ = lf.value("dark theme / bol").toBool();
    bool prolist = lf.value("prolist / bol").toBool();
    MainWindow::resize(lf.value("size / f").toSize());
    ui->action->setChecked(dark_);
    ui->action_2->setChecked(prolist);
    MainWindow::on_action_triggered(dark_);
    MainWindow::on_action_2_triggered(prolist);

    connect(ui->minimize, &QToolButton::clicked, this, &QWidget::showMinimized);

    connect(ui->resize, &QToolButton::clicked, [this](){

            if (this->isMaximized()) {

                ui->resize->setStyleSheet(StyleHelper::getMaximizeStyleSheet(dark_));
                this->showNormal();
            } else {
                ui->resize->setStyleSheet(StyleHelper::getRestoreStyleSheet(dark_));
                this->showMaximized();
            }
        });
    connect(ui->close, &QToolButton::clicked, this, &QWidget::close);


    this->setWindowFlags(Qt::CustomizeWindowHint);

    QTimer *timer = new QTimer();
    connect(timer, SIGNAL(timeout()), this, SLOT(setState()));

    timer->start(10);

}



MainWindow::~MainWindow()
{
    delete ui;
}


void MainWindow::on_pushButton_clicked()
{


    QString r = QDir::currentPath() + "\\ZN\\image.jpg";

    if (QFile::exists(r))
    {
        QString l = QDir::currentPath() + "\\my\\jk.jpg";

        QString pyt = QDir::currentPath() + "\\ZN\\text.txt";
        QFile f(pyt);
        f.open(QIODevice::ReadOnly);
        if (f.size() == 0)
        {
            line1 = "";
        }
        else
        {
            line1 = f.readLine();
        }

        f.close();

        QFile::remove(pyt);
        QFile::remove(l);
        QFile::copy(r,l);
        QFile::remove(r);



        QPixmap pic(l);
        MainWindow::ui->label->setPixmap(pic);
        MainWindow::otsenka();
        MainWindow::ui->label->setCursor(QCursor(Qt::PointingHandCursor));

    }
    else
    {
        MainWindow::ui->label->setText("Loading... Press one more time on \"ТЫК!\"");
        MainWindow::ui->label->setCursor(QCursor(Qt::ArrowCursor));
        MainWindow::ui->groupBox->setVisible(false);
        MainWindow::ui->groupBox_2->setVisible(false);
        MainWindow::ui->groupBox_3->setVisible(false);

    }

}

bool MainWindow::eventFilter(QObject *watched, QEvent *event)
{
    if(watched == ui->label)
    {
        if(event->type() == QEvent::MouseButtonPress)
        {

            const QPixmap *pic = MainWindow::ui->label->pixmap();
            if (pic !=  nullptr)
            {
                QString filename = QFileDialog::getSaveFileName(
                                    this,
                                    tr("Save Image"),
                                    line,
                                    tr("JPG File (*.jpg)") );
                line = filename;

                if(!filename.isEmpty())
                    {
                       pic->save(filename);
                    }
            }
        }
    }


    return false;
}

void MainWindow::closeEvent(QCloseEvent *event)
{
    ofstream f;
    QString kl = QDir::currentPath() + "\\exit.txt";
    f.open(kl.toStdString());
    f << "1";
    f.close();
    QSettings lf("my", "prog");
    lf.setValue("dark theme / bol", ui->action->isChecked());
    lf.setValue("prolist / bol", ui->action_2->isChecked());
    lf.setValue("size / f", MainWindow::size());
    lf.sync();


    qApp->closeAllWindows();
}


void MainWindow::on_action_triggered(bool checked)
{
    dark_ = checked;
    ui->close->setStyleSheet(StyleHelper::getCloseStyleSheet(dark_));
    ui->minimize->setStyleSheet(StyleHelper::getMinimizeStyleSheet(dark_));
    if (this->isMaximized())
        ui->resize->setStyleSheet(StyleHelper::getRestoreStyleSheet(dark_));
    else
        ui->resize->setStyleSheet(StyleHelper::getMaximizeStyleSheet(dark_));
    ui->toolButton->setStyleSheet(StyleHelper::getMenuStyleSheet(dark_));
    if (checked)
    {
        QPalette darkPalette;


            darkPalette.setColor(QPalette::Window, QColor(41, 41, 41));
            darkPalette.setColor(QPalette::WindowText, Qt::white);
            darkPalette.setColor(QPalette::Base, QColor(41, 41, 41));
            darkPalette.setColor(QPalette::AlternateBase, QColor(41, 41, 41));
            darkPalette.setColor(QPalette::ToolTipBase, Qt::white);
            darkPalette.setColor(QPalette::ToolTipText, Qt::white);
            darkPalette.setColor(QPalette::Text, Qt::white);
            darkPalette.setColor(QPalette::Button, QColor(26, 24, 24));
            darkPalette.setColor(QPalette::ButtonText, Qt::white);
            darkPalette.setColor(QPalette::BrightText, Qt::red);
            darkPalette.setColor(QPalette::Link, QColor(42, 130, 218));
            darkPalette.setColor(QPalette::Highlight, QColor(42, 130, 218));
            darkPalette.setColor(QPalette::HighlightedText, Qt::black);


            qApp->setPalette(darkPalette);

    }
    else
    {
        qApp->setPalette(style()->standardPalette());

    }

    QSettings lf("my", "prog");
    lf.setValue("dark theme / bol", ui->action->isChecked());
    lf.setValue("prolist / bol", ui->action_2->isChecked());
    lf.setValue("size / f", MainWindow::size());
    lf.sync();
}



void MainWindow::on_action_2_triggered(bool check)
{
    check_2 = check;
}

void MainWindow::setState()
{
    ifstream f;
    QString pyt = QDir::currentPath() + "\\KN\\ping.txt";
    f.open(pyt.toStdString());
    int state;
    f >> state;
    if (state == 1)
        MainWindow::ui->label_3->setStyleSheet(greenSS);
    else
        MainWindow::ui->label_3->setStyleSheet(redSS);
    f.close();
}

void MainWindow::otsenka()
{
    QString l;
    int i = 0;

    while(line1[i] != '#' && (i < line1.size()))
    {
        l[i] = line1[i];
        i++;

    }

    if (i == 0)
    {
        fstream f1;
        QString pyt1 = QDir::currentPath() + "\\ZN\\mark.txt";
        f1.open(pyt1.toStdString(), fstream::in | fstream::app);
        f1 << mark.toStdString();
        mark = "";        
        MainWindow::ui->pushButton->setEnabled(true);       
        MainWindow::ui->groupBox->setVisible(false);
        MainWindow::ui->groupBox_2->setVisible(false);
        MainWindow::ui->groupBox_3->setVisible(false);

            if (check_2)
            {

                return MainWindow::on_pushButton_clicked();

            }

        return;
    }
    else if (l[3] == '-')
    {
        QString l1 = "Связано ли слово \"" + l[7] + "\" с картинкой?";
        MainWindow::ui->label_2->setText(l1);
        MainWindow::ui->groupBox->setVisible(true);
        MainWindow::ui->groupBox_2->setVisible(false);
        MainWindow::ui->groupBox_3->setVisible(true);
        MainWindow::ui->pushButton->setEnabled(false);
    }
    else if(l[0] == 's')
    {
        QString l1 = "Связаны ли по смыслу слова \"" + l[4] + "\" и \"" + l[6] + "\"?";

        MainWindow::ui->label_2->setText(l1);
        MainWindow::ui->groupBox->setVisible(true);
        MainWindow::ui->groupBox_2->setVisible(false);
        MainWindow::ui->groupBox_3->setVisible(true);
        MainWindow::ui->pushButton->setEnabled(false);
    }
    else if (l[0] == 'o')
    {
        QString l1 = "Связаны ли орфографически слова \"" + l[4] + "\" и \"" + l[6] + "\"?";
        MainWindow::ui->label_2->setText(l1);
        MainWindow::ui->groupBox->setVisible(true);
        MainWindow::ui->groupBox_2->setVisible(false);
        MainWindow::ui->groupBox_3->setVisible(true);
        MainWindow::ui->pushButton->setEnabled(false);
    }
    else if (l[0] == 'p')
    {
        QString l1 = "К какой части речи относится слово \"" + l[4] + "\"?";
        MainWindow::ui->label_2->setText(l1);
        MainWindow::ui->groupBox->setVisible(true);
        MainWindow::ui->groupBox_3->setVisible(false);
        MainWindow::ui->groupBox_2->setVisible(true);
        MainWindow::ui->pushButton->setEnabled(false);

    }
    else
    {
        QString l1 = "Правильно ли орфографически написано слово \"" + l[3] + "\"?";
        MainWindow::ui->label_2->setText(l1);
        MainWindow::ui->groupBox->setVisible(true);
        MainWindow::ui->groupBox_2->setVisible(false);
        MainWindow::ui->groupBox_3->setVisible(true);
        MainWindow::ui->pushButton->setEnabled(false);
    }
    line1.remove(0, l.size() + 1);

}

void MainWindow::on_pushButton_2_clicked()
{
    if (line1.size() == 0)
        mark += "1";
    else
        mark += "1#";


    MainWindow::otsenka();
}

void MainWindow::on_pushButton_3_clicked()
{
    if (line1.size() == 0)
        mark += "2";
    else
        mark += "2#";

    MainWindow::otsenka();
}

void MainWindow::on_pushButton_4_clicked()
{
    if (line1.size() == 0)
        mark += "существительное";
    else
        mark += "существительное#";

    MainWindow::otsenka();
}

void MainWindow::on_pushButton_5_clicked()
{
    if (line1.size() == 0)
        mark += "глагол";
    else
        mark += "глагол#";

    MainWindow::otsenka();
}

void MainWindow::on_pushButton_6_clicked()
{

    if (line1.size() == 0)
        mark += "прилагательное";
    else
        mark += "прилагательное#";

    MainWindow::otsenka();
}

void MainWindow::on_pushButton_7_clicked()
{
    if (line1.size() == 0)
        mark += "нет";
    else
        mark += "нет#";

    MainWindow::otsenka();
}


void MainWindow::on_toolButton_clicked(bool checked)
{
    ui->toolButton->showMenu();
}

void MainWindow::mousePressEvent(QMouseEvent *event)
{

    if (event->button() == Qt::LeftButton )
    {
        QRectF rectInterface(x() + 15, y() + 9, width() - 30, 30);

        if (rectInterface.contains(event->screenPos()))
        {
            m_leftMouseButtonPressed = Move;
            setPreviousPosition(event->pos());
        }
    }
    return QWidget::mousePressEvent(event);
}

void MainWindow::mouseReleaseEvent(QMouseEvent *event)
{

    if (event->button() == Qt::LeftButton) {
        m_leftMouseButtonPressed = None;
    }
    return QWidget::mouseReleaseEvent(event);
}

void MainWindow::mouseMoveEvent(QMouseEvent *event)
{

    if (m_leftMouseButtonPressed == Move)
    {

        if (isMaximized())
        {

            ui->resize->setStyleSheet(StyleHelper::getMaximizeStyleSheet(dark_));

            auto part = event->screenPos().x() / this->width();

            this->showNormal();
            auto offsetX = this->minimumWidth() * part;
            this->setGeometry(event->screenPos().x() - offsetX, event->screenPos().y(), minimumWidth(), minimumHeight());

            setPreviousPosition(QPoint(offsetX, event->screenPos().y()));
        } else
        {
            auto dx = event->x() - m_previousPosition.x();
            auto dy = event->y() - m_previousPosition.y();
            setGeometry(x() + dx, y() + dy, width(), height());
        }

    }


    return QWidget::mouseMoveEvent(event);
}

void MainWindow::setPreviousPosition(QPoint previousPosition)
{
    if (m_previousPosition == previousPosition)
        return;

    m_previousPosition = previousPosition;

}


void MainWindow::on_action_3_triggered()
{

    if(!avtors)
    {
        Main_Window* Form = new Main_Window;
        Form->ui->pushButton->setVisible(false);
        Form->ui->toolButton->setVisible(false);
        Form->ui->resize->setVisible(false);
        Form->ui->label_3->setVisible(false);        
        Form->setMinimumSize(250, 250);
        Form->setGeometry(x() + width()/2 - 125,y() + height()/2 - 125,250,250);
        Form->ui->label->setText("Студенты группы ИКБО-02-19:\n"
                                 "Сюртуков Захар Александрович\n"
                                 "Остранков Константин Дмитриевич\n"
                                 "Иноземцев Никита Сергеевич");
        Form->setAttribute(Qt::WA_DeleteOnClose, true);
        Form->setWindowFlag(Qt::WindowStaysOnTopHint);

        Form->show();
        avtors = true;
    }
}
