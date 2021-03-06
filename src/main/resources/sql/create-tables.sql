CREATE TABLE AREAS (ID SERIAL NOT NULL, AREA JSONB, CONSTRAINT areas_pkey PRIMARY KEY (ID));
CREATE TABLE ARTISTS (ID SERIAL NOT NULL, ARTIST JSONB, CONSTRAINT artists_pkey PRIMARY KEY (ID));
CREATE TABLE FORMATS (ID SERIAL NOT NULL, FORMAT JSONB, CONSTRAINT formats_pkey PRIMARY KEY (ID));
CREATE TABLE LABELS (ID SERIAL NOT NULL, LABEL JSONB, CONSTRAINT labels_pkey PRIMARY KEY (ID));
CREATE TABLE LANGUAGES (ID SERIAL NOT NULL, LANGUAGE JSONB, CONSTRAINT languages_pkey PRIMARY KEY (ID));
CREATE TABLE MEDIUMS (ID SERIAL NOT NULL, MEDIUM JSONB, CONSTRAINT mediums_pkey PRIMARY KEY (ID));
CREATE TABLE PLACES (ID SERIAL NOT NULL, PLACE JSONB, CONSTRAINT places_pkey PRIMARY KEY (ID));
CREATE TABLE RECORDS (ID SERIAL NOT NULL, RECORD JSONB, CONSTRAINT records_pkey PRIMARY KEY (ID));
CREATE TABLE RELEASES (ID SERIAL NOT NULL, RELEASE JSONB, CONSTRAINT releases_pkey PRIMARY KEY (ID));
CREATE TABLE TRACKS (ID SERIAL NOT NULL, TRACK JSONB, CONSTRAINT tracks_pkey PRIMARY KEY (ID));

DROP TABLE AREAS;
DROP TABLE ARTISTS;
DROP TABLE FORMATS;
DROP TABLE LABELS;
DROP TABLE LANGUAGES;
DROP TABLE MEDIUMS;
DROP TABLE PLACES;
DROP TABLE RECORDS;
DROP TABLE RELEASES;
DROP TABLE TRACKS;


SELECT RECORDS.* from tracks JOIN records ON record->'id' = track->'recordId' JOIN ARTISTS ON artist->'id' = record->'artistID' GROUP BY RECORDS.ID
SELECT FORMATS.* from RELEASES JOIN MEDIUMS ON release->'mediumId' = medium->'id' JOIN FORMATS ON FORMAT->'id' = MEDIUM->'formatId'


SELECT jsonb_set(TRACK, '{testAreaId}'::text[], RECORD->'artistId',true) from TRACKS INNER JOIN RECORDS ON RECORD->'id' = TRACK->'recordId';

UPDATE TRACKS
SET TRACK = jsonb_set(TRACK, '{testAreaId}'::text[], RECORD->'artistId',true)
FROM RECORDS
WHERE RECORD->'id' = TRACK->'recordId';

UPDATE artists
SET artist = artist || '{"areaID": 1}'
WHERE artist @> '{"id":11000}';

SELECT AREA FROM AREAS WHERE AREA->'id' = 1












SELECT area
FROM areas
WHERE area @> '{"id":999}';

UPDATE artists
SET artist = artist || '{"areaID": area' + area->'id' + '}'
FROM areas
WHERE ARTIST->'areaId' = AREA->'id';

SELECT jsonb_set(ARTIST, '{testAreaId}'::text[], AREA->'id',true) from ARTISTS LEFT JOIN AREAS ON ARTIST->'areaID' = AREA->'ID';
SELECT jsonb_set(ARTIST, '{location}', ARTIST->'location' || '{"city": "ottawa", "phone": "phonenum", "prefix": "prefixedName"}') FROM ARTISTS


SELECT jsonb '{"top": {"nested": {"leaf" : 1}}}' #- '{top,nested,leaf}'


CREATE TABLE AREAS (ID SERIAL NOT NULL, AREA JSONB, CONSTRAINT areas_pkey PRIMARY KEY (ID));
CREATE TABLE ARTISTS (ID SERIAL NOT NULL, ARTIST JSONB, CONSTRAINT artists_pkey PRIMARY KEY (ID));
CREATE TABLE FORMATS (ID SERIAL NOT NULL, FORMAT JSONB, CONSTRAINT formats_pkey PRIMARY KEY (ID));
CREATE TABLE LABELS (ID SERIAL NOT NULL, LABEL JSONB, CONSTRAINT labels_pkey PRIMARY KEY (ID));
CREATE TABLE LANGUAGES (ID SERIAL NOT NULL, LANGUAGE JSONB, CONSTRAINT languages_pkey PRIMARY KEY (ID));
CREATE TABLE MEDIUMS (ID SERIAL NOT NULL, MEDIUM JSONB, CONSTRAINT mediums_pkey PRIMARY KEY (ID));
CREATE TABLE PLACES (ID SERIAL NOT NULL, PLACE JSONB, CONSTRAINT places_pkey PRIMARY KEY (ID));
CREATE TABLE RECORDS (ID SERIAL NOT NULL, RECORD JSONB, CONSTRAINT records_pkey PRIMARY KEY (ID));
CREATE TABLE RELEASES (ID SERIAL NOT NULL, RELEASE JSONB, CONSTRAINT releases_pkey PRIMARY KEY (ID));
CREATE TABLE TRACKS (ID SERIAL NOT NULL, TRACK JSONB, CONSTRAINT tracks_pkey PRIMARY KEY (ID));



SELECT jsonb_set(TRACK, '{testAreaId}'::text[], RECORD->'artistId',true) from TRACKS INNER JOIN RECORDS ON RECORD->'id' = TRACK->'recordId';

UPDATE TRACKS
SET TRACK = jsonb_set(TRACK, '{testAreaId}'::text[], RECORD->'artistId',true)
FROM RECORDS
WHERE RECORD->'id' = TRACK->'recordId';

UPDATE AREAS
SET AREA = AREA - 'comment'
WHERE AREA->'id' > '99';

SELECT AREA FROM AREAS WHERE AREA->'id' = '1'

DELETE
FROM AREAS
WHERE AREA->'id' > '99' OR AREA->'id' < '0'