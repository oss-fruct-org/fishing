#include <QtGui/QApplication>
#include <QGLWidget>
#include <QtDeclarative>
#include <qplatformdefs.h>
#include <imagewithborder.h>

Q_DECL_EXPORT int main(int argc, char *argv[])
{
    QApplication app(argc, argv);
    QDeclarativeView view;

//    NetworkAccessManagerFactory* namFactory = new NetworkAccessManagerFactory();
//    namFactory->setProxy(QNetworkProxy::HttpProxy, "127.0.0.1", 8888);
//    view.engine()->setNetworkAccessManagerFactory(namFactory);

    view.setViewport(new QGLWidget());
    QObject::connect((QObject*)view.engine(), SIGNAL(quit()), &app, SLOT(quit()));

    qmlRegisterType<ImageWithBorder>("fishing", 1, 0, "ImageWithBorder");

#ifdef MEEGO_EDITION_HARMATTAN
    view.setSource(QUrl(QString(DATAPREFIX).append("/qml/main.qml")));
#else
    view.setSource(QUrl("../../../qml/fishing/main.qml"));
#endif
    view.showFullScreen();

    return app.exec();
}
