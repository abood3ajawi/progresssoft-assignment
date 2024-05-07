CREATE DATABASE IF NOT EXISTS bloomberg_fx_deals;

CREATE USER 'fx_app'@'%' IDENTIFIED BY 'fx_app1';
GRANT ALL PRIVILEGES ON bloomberg_fx_deals.* TO 'fx_app'@'%';

USE bloomberg_fx_deals;

CREATE TABLE fx_deals (
                          deal_id VARCHAR(50) PRIMARY KEY,
                          from_currency_iso CHAR(3) NOT NULL,
                          to_currency_iso CHAR(3) NOT NULL,
                          deal_timestamp TIMESTAMP NOT NULL,
                          deal_amount DECIMAL(20, 5) NOT NULL,

                          CONSTRAINT chk_from_currency_iso CHECK (LENGTH(from_currency_iso) = 3),
                          CONSTRAINT chk_to_currency_iso CHECK (LENGTH(to_currency_iso) = 3),
                          CONSTRAINT chk_deal_amount CHECK (deal_amount > 0)
);

CREATE INDEX idx_fx_deals_deal_id ON fx_deals (deal_id);


CREATE TABLE iso_currency (
                              CurrencyCode CHAR(3),
                              PRIMARY KEY (CurrencyCode)
);




INSERT IGNORE INTO iso_currency (CurrencyCode)
VALUES
('AFN'), ('ALL'), ('DZD'), ('USD'), ('EUR'), ('AOA'), ('XCD'), ('ARS'), ('AMD'), ('AWG'),
('AUD'), ('EUR'), ('AZN'), ('BSD'), ('BHD'), ('BDT'), ('BBD'), ('BYN'), ('EUR'), ('BZD'),
('XOF'), ('BMD'), ('BTN'), ('INR'), ('BOB'), ('BOV'), ('USD'), ('BAM'), ('BWP'), ('NOK'),
('BRL'), ('USD'), ('BGN'), ('XOF'), ('BIF'), ('CVE'), ('KHR'), ('XAF'), ('CAD'), ('KYD'),
('XAF'), ('XAF'), ('CLF'), ('CLP'), ('CNY'), ('AUD'), ('AUD'), ('COP'), ('COU'), ('KMF'),
('CDF'), ('XAF'), ('NZD'), ('CRC'), ('EUR'), ('CUP'), ('ANG'), ('EUR'), ('CZK'), ('XOF'),
('DKK'), ('DJF'), ('XCD'), ('DOP'), ('USD'), ('EGP'), ('SVC'), ('USD'), ('XAF'), ('ERN'),
('EUR'), ('ETB'), ('EUR'), ('FKP'), ('DKK'), ('FJD'), ('EUR'), ('EUR'), ('EUR'), ('EUR'),
('XPF'), ('EUR'), ('XAF'), ('GMD'), ('GEL'), ('EUR'), ('GHS'), ('GIP'), ('EUR'), ('EUR'),
('XCD'), ('USD'), ('GTQ'), ('GBP'), ('GNF'), ('XOF'), ('GYD'), ('HTG'), ('USD'), ('AUD'),
('EUR'), ('HNL'), ('HKD'), ('HUF'), ('ISK'), ('INR'), ('IDR'), ('XDR'), ('IRR'), ('IQD'),
('EUR'), ('GBP'), ('ILS'), ('EUR'), ('JMD'), ('JPY'), ('GBP'), ('JOD'), ('KZT'), ('KES'),
('AUD'), ('KPW'), ('KRW'), ('KWD'), ('KGS'), ('LAK'), ('EUR'), ('LBP'), ('LSL'), ('ZAR'),
('LRD'), ('LYD'), ('CHF'), ('EUR'), ('EUR'), ('MOP'), ('MGA'), ('MWK'), ('MYR'), ('MVR'),
('XOF'), ('EUR'), ('EUR'), ('USD'), ('EUR'), ('MRO'), ('MUR'), ('EUR'), ('XUA'), ('MXN'),
('MXV'), ('USD'), ('MDL'), ('EUR'), ('MNT'), ('EUR'), ('XCD'), ('MAD'), ('MZN'), ('MMK'),
('NAD'), ('ZAR'), ('AUD'), ('NPR'), ('EUR'), ('XPF'), ('NZD'), ('NIO'), ('XOF'), ('NGN'),
('NZD'), ('NOK'), ('OMR'), ('PKR'), ('USD'), ('PAB'), ('USD'), ('PGK'), ('PYG'), ('PEN'),
('PHP'), ('NZD'), ('PLN'), ('EUR'), ('USD'), ('QAR'), ('MKD'), ('RON'), ('RUB'), ('RWF'),
('EUR'), ('EUR'), ('SHP'), ('XCD'), ('XCD'), ('EUR'), ('EUR'), ('XCD'), ('WST'), ('EUR'),
('STN'), ('SAR'), ('XOF'), ('RSD'), ('SCR'), ('SLL'), ('SGD'), ('ANG'), ('XSU'), ('EUR'),
('EUR'), ('SBD'), ('SOS'), ('ZAR'), ('USD'), ('SSP'), ('EUR'), ('LKR'), ('SDG'), ('SRD'),
('NOK'), ('SZL'), ('SEK'), ('CHF'), ('CHF'), ('CHW'), ('SYP'), ('TWD'), ('TJS'), ('TZS'),
('THB'), ('USD'), ('XOF'), ('NZD'), ('TOP'), ('TTD'), ('TND'), ('TRY'), ('TMT'), ('USD'),
('AUD'), ('UGX'), ('UAH'), ('AED'), ('GBP'), ('USD'), ('USD'), ('UYI'), ('UYU'), ('UZS'),
('VUV'), ('VEF'), ('VED'), ('VND'), ('USD'), ('USD'), ('XPF'), ('MAD'), ('YER'), ('ZMW'),
('ZWL');
