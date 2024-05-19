import random
import time

from selenium import webdriver
from selenium.webdriver import Keys
from selenium.webdriver.common.by import By

if __name__ == '__main__':
    browser = webdriver.Chrome()
    browser.get('https://www.google.com')
    ele = browser.switch_to.active_element
    time.sleep(random.randint(1, 5))
    ele.send_keys("start a business by ai (devz ai)")
    ele.send_keys(Keys.ENTER)
    time.sleep(random.randint(1, 10))
    ele = browser.find_element(By.PARTIAL_LINK_TEXT, "Devz AI - Build Your Dream With AI")
    ele.click()
    time.sleep(30 + random.randint(-5, 5))
    ele = browser.find_element(By.PARTIAL_LINK_TEXT, "Devz Forge")
    ele.click()
    time.sleep(15 + random.randint(-5, 5))
    ele = browser.find_element(By.PARTIAL_LINK_TEXT, "Devz Sentry")
    ele.click()
    time.sleep(15 + random.randint(-5, 5))
    ele = browser.find_element(By.PARTIAL_LINK_TEXT, "How Devz Works")
    ele.click()
    time.sleep(15 + random.randint(-5, 5))
