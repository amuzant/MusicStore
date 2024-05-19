insert into proiectpao.debitcard(codCard, balanta, banca, limita, dataExpirare) values('12345678',1550.53,'BT',105,'2028-05-05');
insert into proiectpao.debitcard(codCard, balanta, banca, limita, dataExpirare) values('11112222',101.22,'BRD',25,'2026-05-05');

insert into proiectpao.user(id, nume, email, nrTelefon, adresa, dataAlaturarii, card_cod) values(1,'Andrei Simon','asimon@test.com','072123456','Splaiul Independentei 201',sysdate(),'12345678');
insert into proiectpao.user(id, nume, email, nrTelefon, adresa, dataAlaturarii, card_cod) values(2,'Costel Arbore','carbore@test2.com','072999888','Iuliu Maniu 123',sysdate(),'11112222');

insert into proiectpao.produs(id, denumire, pret, conditie, stoc, rating, nrReviewuri) values(1,'Chitara Electrica Rock Fender',500,'Nou',2,0,0);
insert into proiectpao.chitara(culoare, produs_id) VALUES ('rosu',1);

select * from user;

insert into proiectpao.chitaraelectrica(configuratie, produs_id) VALUES ('H-S-S',1);
insert into proiectpao.produs(id, denumire, pret, conditie, stoc, rating, nrReviewuri) values(2,'Chitara Acustica Jet',100,'Ca Nou',5,0,0);
insert into proiectpao.chitara(culoare, produs_id) VALUES ('albastru',2);
insert into proiectpao.chitaraacustica(forma, produs_id) VALUES ('Dread',2);
insert into proiectpao.album(id, numeArtist, numeAlbum, genMuzical) VALUES (1,'Cocteau Twins','Heaven or Las Vegas','Dream Pop');
insert into proiectpao.produs(id, denumire, pret, conditie, stoc, rating, nrReviewuri) values(3,'Cocteau Twins - Heaven or Las Vegas',20,'Ca Nou',5,0,0);
insert into proiectpao.discalbum(tipDisc, anLansare, numeCasaDeDiscuri, nrDiscuri, pretInchirierePeZi, album_id, produs_id) VALUES ('CD',1990,'4AD',1,6,1,3);

insert into proiectpao.inchiriere(client_id, albumImprumutat_id, dataInchirierii, zileInchiriate, pretPlatit) VALUES (1,3,sysdate(),5,30);
insert into proiectpao.comanda(client_id, pretTotal, dataAchizitiei) VALUES (2,120,sysdate());
insert into proiectpao.produscomandat(comanda_id, produs_id, reviewed) VALUES (1,2,false);
insert into proiectpao.produscomandat(comanda_id, produs_id, reviewed) VALUES (1,3,false);

select * from melodie;

insert into proiectpao.discinterior(id, produs_id, denumire, nrDisc, nrPiese) values (1,3,'Heaven or Las Vegas',1,10);
insert into proiectpao.melodie(discInterior_id, denumire, indexPiesa, durata) VALUES (1,'Cherry-coloured Funk',1,193);
insert into proiectpao.melodie(discInterior_id, denumire, indexPiesa, durata) VALUES (1,'Pitch The Baby',2,197);
insert into proiectpao.melodie(discInterior_id, denumire, indexPiesa, durata) VALUES (1,'Iceblink Luck',3,198);
insert into proiectpao.melodie(discInterior_id, denumire, indexPiesa, durata) VALUES (1,'Fifty-fifty Clown',4,195);
insert into proiectpao.melodie(discInterior_id, denumire, indexPiesa, durata) VALUES (1,'Heaven or Las Vegas',5,297);
insert into proiectpao.melodie(discInterior_id, denumire, indexPiesa, durata) VALUES (1,'I Wear Your Ring',6,220);
insert into proiectpao.melodie(discInterior_id, denumire, indexPiesa, durata) VALUES (1,'Fotzepolitic',7,210);
insert into proiectpao.melodie(discInterior_id, denumire, indexPiesa, durata) VALUES (1,'Wolf In The Breast',8,212);
insert into proiectpao.melodie(discInterior_id, denumire, indexPiesa, durata) VALUES (1,'Road, River and Rail',9,211);
insert into proiectpao.melodie(discInterior_id, denumire, indexPiesa, durata) VALUES (1,'Frou-frou Foxes In Midsummer Fires',10,337);

