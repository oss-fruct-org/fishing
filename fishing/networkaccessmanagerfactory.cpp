#include "networkaccessmanagerfactory.h"

QNetworkAccessManager *NetworkAccessManagerFactory::create(QObject *parent)
{
    // iPhone Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_0 like Mac OS X; en-us) AppleWebKit/532.9 (KHTML, like Gecko) Version/4.0.5 Mobile/8A293 Safari/6531.22.7
    QString userAgent = QString("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_2) AppleWebKit/536.26.14 (KHTML, like Gecko) Version/6.0.1 Safari/536.26.14");
    QNetworkAccessManager *nam = new NetworkAccessManager(userAgent, parent);

    if (!m_proxy.hostName().isEmpty()) {
        qDebug() << "Created QNetworkAccessManager using proxy" << (m_proxy.hostName() + ":" + QString::number(m_proxy.port()));
        nam->setProxy(m_proxy);
    }

    return nam;
}

void NetworkAccessManagerFactory::setProxy(QNetworkProxy::ProxyType type, const QString &hostName, quint16 port)
{
    m_proxy.setHostName(hostName);
    m_proxy.setType(type);
    m_proxy.setPort(port);
}
