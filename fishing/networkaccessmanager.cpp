#include "networkaccessmanager.h"

NetworkAccessManager::NetworkAccessManager(QString userAgent, QObject *parent) :
    QNetworkAccessManager(parent),
    m_userAgent(userAgent)
{
}

QNetworkReply* NetworkAccessManager::createRequest(Operation op, const QNetworkRequest &request, QIODevice *outgoingData)
{
    QNetworkRequest new_req(request);
    new_req.setRawHeader("User-Agent", m_userAgent.toAscii());

    QNetworkReply* reply = QNetworkAccessManager::createRequest(op, new_req, outgoingData);
    return reply;
}
