import matplotlib.pyplot as plt
from tensorflow.keras.models import Model
from tensorflow.keras.layers import Input, Dense, Dropout, LayerNormalization
from tensorflow.keras.layers import MultiHeadAttention, Embedding, Flatten

def build_transformer_model(input_shape, model_size, num_heads, ff_dim, num_transformer_blocks):
    inputs = Input(shape=(input_shape,))
    x = Embedding(input_dim=10000, output_dim=model_size)(inputs)
    
    for _ in range(num_transformer_blocks):
        attn_output = MultiHeadAttention(num_heads=num_heads, key_dim=model_size)(x, x)
        attn_output = Dropout(0.1)(attn_output)
        out1 = LayerNormalization(epsilon=1e-6)(x + attn_output)
        
        ffn_output = Dense(ff_dim, activation="relu")(out1)
        ffn_output = Dense(model_size)(ffn_output)
        ffn_output = Dropout(0.1)(ffn_output)
        x = LayerNormalization(epsilon=1e-6)(out1 + ffn_output)
    
    x = Flatten()(x)
    outputs = Dense(1)(x)  # 预测未来价格
    model = Model(inputs=inputs, outputs=outputs)
    return model
print(Model)
# model = build_transformer_model(input_shape=100, model_size=64, num_heads=8, ff_dim=128, num_transformer_blocks=2)