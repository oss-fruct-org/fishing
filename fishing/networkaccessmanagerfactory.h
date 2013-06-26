#ifndef NETWORKACCESSMANAGERFACTORY_H
#define NETWORKACCESSMANAGERFACTORY_H

#include <QDeclarativeNetworkAccessManagerFactory>
#include <QNetworkProxyFactory>
#include <QNetworkAccessManager>
#include <QNetworkProxy>
#include <networkaccessmanager.h>

class NetworkAccessManagerFactory : public QDeclarativeNetworkAccessManagerFactory
{
public:
    virtual QNetworkAccessManager *create(QObject *parent);
    void setProxy(QNetworkProxy::ProxyType type, const QString& hostName, quint16 port = 0);

private:
    QNetworkProxy m_proxy;
};

#endif // NETWORKACCESSMANAGERFACTORY_H
