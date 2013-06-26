# -*- coding: utf-8 -*-
import os
fish_images = {}

fish_images[u'Ерш'] = 'ersh.jpg'
fish_images['Лещ'] = 'lesh.jpg'
fish_images['Сиг'] = 'sig.jpg'
fish_images['Сом'] = 'som.jpg'
fish_images['Язь'] = 'yaz.jpg'
fish_images['Елец'] = 'elec.jpg'
fish_images['Линь'] = 'lin.jpg'
fish_images['Щука'] = 'shuka.jpg'
fish_images['Голец усатый'] = 'golec_usatui.jpg'
fish_images['Бычок'] = 'bichok.jpg'
fish_images['Жерех'] = 'zereh.jpg'
fish_images['Килец'] = 'kilec.jpg'
fish_images['Налим'] = 'nalim.jpg'
fish_images['Окунь'] = 'okun.jpg'
fish_images['Осетр'] = 'osetr.jpg'
fish_images['Палия'] = 'paliya.jpg'
fish_images['Рипус'] = 'ripus.jpg'
fish_images['Синец'] = 'sinec.jpg'
fish_images['Судак'] = 'sudak.jpg'
fish_images['Сырть'] = 'sirt.jpg'
fish_images['Угорь'] = 'ugor.jpg'
fish_images['Уклея'] = 'uklea.jpg'
fish_images['Минога речная'] = 'minoga_river.jpg'
fish_images['Карась золотой'] = 'karas_zolotoi.jpg'
fish_images['Минога ручьевая'] = 'minoga_ruchei.jpg'
fish_images['Гольян'] = 'golyan.jpg'
fish_images['Карась'] = 'karas.jpg'
fish_images['Лосось'] = 'losos.jpg'
fish_images['Плотва'] = 'plotva.jpg'
fish_images['Форель'] = 'forel.jpg'
fish_images['Хариус'] = 'harius.jpg'
fish_images['Чехонь'] = 'chehon.jpg'
fish_images['Озерная форель'] = 'forel_lake.jpg'
fish_images['Голавль'] = 'golavl.jpg'
fish_images['Густера'] = 'gustera.jpg'
fish_images['Колюшка'] = 'kolushka.jpg'
fish_images['Корюшка'] = 'korushka.jpg'
fish_images['Пескарь'] = 'peskar.jpg'
fish_images['Ряпушка'] = 'ryapushka.jpg'
fish_images['Щиповка'] = 'shipovka.jpg'
fish_images['Белоглазка'] = 'beloglazka.jpg'
fish_images['Красноперка'] = 'krashoperka.jpg'
fish_images['Подкаменщик'] = 'podkamenshik.jpg'

for filename in os.listdir("."):
	if (filename.endswith(".jpg")): 
		try:
			os.rename(filename, fish_images[filename[:-4]])
		except KeyError: 
			print ""
	# if filename.startswith("cheese_"):
	# 	os.rename(filename, filename[7:])