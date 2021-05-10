import smbus
import time
#classe per leggere il valore dell'umidità del terreno
#viene utilizzato il modulo ADS1115 per leggere il valore analogico del sensore
#fonte: https://github.com/ControlEverythingCommunity/ADS1115/blob/master/Python/ADS1115.py
bus = smbus.SMBus(1)

data = [0x84,0x83]
bus.write_i2c_block_data(0x48, 0x01, data)

time.sleep(0.5)

data = bus.read_i2c_block_data(0x48, 0x00, 2)

raw_adc = data[0] * 256 + data[1]

if raw_adc > 32767:
	raw_adc -= 65535

print (raw_adc)
