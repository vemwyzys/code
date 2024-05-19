import json
import boto3

def lambda_handler(event, context):
    s3 = boto3.resource('s3')
    s3.meta.client.upload_file('ameng-melina', 'test.txt')

    string = "dfghj"
    encoded_string = string.encode("utf-8")

    bucket_name = "ameng-melina"
    file_name = "hello.txt"
    s3_path = "20220523" + file_name

    s3 = boto3.resource("s3")
    s3.Bucket(bucket_name).put_object(Key=s3_path, Body=encoded_string)

    return {
        'statusCode': 200,
        'body': json.dumps("hello world")
    }
